package cn.com.jd.util;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by jihaixiao on 2017/1/24.
 */
public class MyArrayBlockingQueue<E> {

    //队列数组
    private final Object[] items;

    //添加元素索引，最大值是items.length
    private int addIndex;

    //删除元素索引，最大值是items.length
    private int removeIndex;

    //当前队列数组元素数量
    private int count;

    //锁
    private final Lock lock;

    //删除元素Condition
    private final Condition notEmpty;

    //添加元素Condition
    private final Condition notFull;

    /**
     *
     * 构造器，此处items，lock，notEmpty，notFull 都被final修饰
     * 都具有final的内存语义，能够保证在引用构造过程中没有被溢出的
     * 情况下，MyArrayBlockingQueue实例可以被正确构造，所有线程都
     * 能读到正确的items，lock，notEmpty，notFull的值。
     *
     * @param size
     */
    public MyArrayBlockingQueue(int size) {
        items = new Object[size];
        lock = new ReentrantLock();
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
    }

    /**
     * 往队列中添加元素，如果队列满了，当前添加线程阻塞，直到队列有空位位置
     * @param e
     * @throws InterruptedException
     */
    public void add(E e) throws InterruptedException {
        /**
         * 1：想使用Condition，先获取它对应的锁再说
         */
        lock.lock();
        try {
            /**
             * 2:循环检测队列的长度，如果满了，调用notFull这个Condition的
             * await()方法让当前add方法的线程进入等待状态，如果队列随后有空位了，
             * 那么该线程被唤醒，发现不符合count == item.length条件，就能跳出此
             * 循环
             */
            while (count == items.length) {
                notFull.await();
            }
            /**
             * 3：把添加的元素放到指定的索引上
             */
            items[addIndex] = e;
            /**
             * 4：此处如果元素索引已经达到了队列最大值，那么要
             * 置0，从头开始。因为这是个先进先出FIFO队列，所以，元素被取走
             * 都是从头部开始取，头部肯定是先出空位的
             * (此处对普通int类型addIndex 进行++操作能保证原子性和可见性，是因为
             * 这个操作被锁住了)
             */
            if (++addIndex == items.length)
                addIndex = 0;
            /**
             * 5：添加完元素，数组当前元素数量+1,此处操作被锁住，所以可以保证
             * 线程安全
             */
            ++count;
            /**
             * 6：添加完元素，队列里面有元素了，此时调用下sigal()方法，去唤醒一个
             * 在notEmpty等待队列里面等着取元素的线程。
             */
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 从队列头移除元素，如果队列空了，那么当前移除元素线程会被阻塞，直到队列中有元素了
     * @return
     * @throws InterruptedException
     */
    public E remove() throws InterruptedException {
        /**
         * 1：想使用Condition，先获取它对应的锁再说
         */
        lock.lock();
        try {
            /**
             * 2：循环检测队列当前元素的数量，如果为0了，表示
             * 当前队列为空，此时不能取元素了，要阻塞当前remove的线程
             * 并将该线程加入到notEmpty的等待队列中，如果当前线程被唤醒
             * 表示队列有元素了，那么就不符合count == 0这个条件，就能跳出
             * 这个循环。
             */
            while (count == 0){
                notEmpty.await();
            }
            /**
             * 3：根据删除元素索引拿到被remove的元素o
             */
            Object o = items[removeIndex];
            /**
             * 4：将该数组中被删除元素索引那儿的引用置为null,便于GC
             */
            items[removeIndex] = null;
            /**
             * 5：如果被移出元素索引达到队列长度，那么
             * 就会被removeIndex置为0，从头开始
             */
            if (++removeIndex == items.length)
                removeIndex = 0;
            /**
             * 6：当前队列中元素数量-1，因为锁，保证线程安全
             */
            --count;
            /**
             * 7：删除完元素，尝试去唤醒一个在notNull队列里
             * 等待的添加元素的线程。
             */
            notFull.signal();
            return (E) o;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(items);
    }
}
