package cn.com.jd.util;

import sun.misc.Unsafe;

import java.util.concurrent.locks.AbstractOwnableSynchronizer;

/**
 * Created by jihaixiao on 2016/12/13.
 */
public abstract class AbstractQueuedSynchronizer extends AbstractOwnableSynchronizer {

    private static final Unsafe unsafe = Unsafe.getUnsafe();
    private static final long stateOffset;
    private static final long headOffset;
    private static final long tailOffset;
    private static final long waitStatusOffset;
    private static final long nextOffset;

    static {
        try {
            stateOffset = unsafe.objectFieldOffset
                    (AbstractQueuedSynchronizer.class.getDeclaredField("state"));
            headOffset = unsafe.objectFieldOffset
                    (AbstractQueuedSynchronizer.class.getDeclaredField("head"));
            tailOffset = unsafe.objectFieldOffset
                    (AbstractQueuedSynchronizer.class.getDeclaredField("tail"));
            waitStatusOffset = unsafe.objectFieldOffset
                    (Node.class.getDeclaredField("waitStatus"));
            nextOffset = unsafe.objectFieldOffset
                    (Node.class.getDeclaredField("next"));

        } catch (Exception ex) { throw new Error(ex); }
    }

    /**
     * 等待队列的队列头，调用setHead方法会修改头
     */
    private transient volatile Node head;

    /**
     * 等待队列的队尾，调用enq方法会增加一个新的node作为队尾
     */
    private transient volatile Node tail;


    public final void acquire(int acquires){

    }

    protected boolean tryAcquire(int arg) {
        throw new UnsupportedOperationException();
    }


    final boolean acquireQueued(final Node node,int arg){
        boolean failed = true;
        try {
            boolean interrupted = false;
            for (;;){
                //1:拿到刚进队的节点的前置节点
                final Node p = node.predecessor();
                if (p == head && tryAcquire(arg)){
                    //2:如果它的前置节点是头节点，并且它尝试修改同步状态成功了,
                    //表示获取到了同步状态，因为设置头结点是成功获取到状态的线程来完成，所以此处设置一下头节点
                    setHead(node);
                    //3：把曾经的头结点释放掉
                    p.next = null;
                    failed = false;
                    return interrupted;
                }
            }
        } finally {

        }
    }

    private static boolean shouldParkAfterFailedAcquire(Node pred,Node node){
        int ws = pred.waitStatus;
        if (ws == Node.SIGNAL)
            return true;
        //如果节点的状态大于0，只有取消状态大于0
        //然后跳过那些已经被取消的节点
        if (ws > 0) {
            do {
                node.prev = node = pred.prev;
            } while (pred.waitStatus > 0);
            pred.next = node;
        } else {
            compareAndSetWaitStatus(pred,ws,Node.SIGNAL);
        }
        return false;
    }

    private void setHead(Node node){
        head = node;
        node.thread = null;
        node.prev = null;
    }


    /**
     *
     * 调用addWaiter方法将当前线程引用，状态封装成一个Node，然后加入到CLH队列里面
     *
     * @param mode
     * @return
     */
    private Node addWaiter(Node mode){
        //1:将当前线程引用，状态封装成一个node对象。
        Node node = new Node(Thread.currentThread(),mode);
        Node pred = tail;
        if (pred != null){
            //2:如果队尾不是空，那么把当前node的前置节点置为曾经的队尾，当前节点作为新队尾
            node.prev = pred;
            //3:CAS方式把AQS的tail属性改为新的节点，如果成功，把曾经队尾的节点的后置节点改为当前节点
            if (compareAndSetTail(pred,node)){
                pred.next = node;
                return node;
            }
        }
        //4:如果前面CAS失败，那么会走enq 轮询去加入队列直至成功，前面那块代码可能为了节约性能吧
        enq(node);
        return node;
    }

    /**
     *
     * 为等待队列增加新节点的通用方法，当队尾为空（表示此时队列还不存在），那么就新创建一个，作为队头
     *
     * 然后把新的node置为队尾。
     *
     * @param node
     * @return
     */
    private Node enq(final Node node){
        for (;;){
            Node t = tail;
            if (t == null){
                if (compareAndSetHead(new Node())){
                    tail = head;
                }
            } else {
                node.prev = t;
                if (compareAndSetTail(t,node)){
                    t.next = node;
                    return t;
                }
            }
        }
    }


    static final class Node{

        /**
         *  节点状态：取消，在同步队列中的线程等待超时或者被中断，就会变成这个状态
         *
         */
        static final int CANCELLED =  1;


        static final int SIGNAL    = -1;

        static final int CONDITION = -2;

        static final int PROPAGATE = -3;

        /**
         *
         * 等待状态，节点上线程的等待状态，详情见表格
         *
         */
        volatile int waitStatus;

        /**
         *
         * 同步队列Node的前置节点，volatile修饰，确保对它的修改能被其他
         * 线程可见
         */
        volatile Node prev;

        /**
         *
         * 同步队列的Node的后继节点，volatile修饰，确保对它的修改能被其他
         * 线程可见
         */
        volatile Node next;

        /**
         *
         * 获取同步状态失败的线程引用，volatile修饰，确保对它的修改能够对其他线程
         * 读到
         */
        volatile Thread thread;

        /**
         *
         * 等待队列中的后继节点，如果当前节点是共享的，那么这个字段将是一个SHARED常量
         * 也就是说节点类型和等待队列中的后继节点共用同一个字段。
         *
         */
        Node nextWaiter;

        Node(){}

        Node(Thread thread,Node mode){
            this.thread = thread;
            this.nextWaiter = mode;
        }

        /**
         * 获取当前节点的前置节点，如果为空，抛空指针异常
         * @return
         * @throws NullPointerException
         */
        final Node predecessor() throws NullPointerException{
            Node p = prev;
            if ( p == null){
                throw new NullPointerException();
            } else {
                return p;
            }
        }

    }

    private final boolean compareAndSetHead(Node update) {
        return unsafe.compareAndSwapObject(this, headOffset, null, update);
    }

    private final boolean compareAndSetTail(Node expect, Node update) {
        return unsafe.compareAndSwapObject(this, tailOffset, expect, update);
    }

    private static final boolean compareAndSetWaitStatus(Node node,
                                                         int expect,
                                                         int update) {
        return unsafe.compareAndSwapInt(node, waitStatusOffset,
                expect, update);
    }
}
