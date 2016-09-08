package cn.com.jd.datastructure;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by jihaixiao on 2016/9/7.
 *
 *
 *  阻塞队列 FIFO
 *
 */
public class BoundedQueue<E> implements Serializable{

    private E[] items;

    //队列里的元素数量
    private int count;

    //入队索引
    private int addIndex;

    //出队索引
    private int removeIndex;

    //锁，多个线程出入队共用这一把锁
    private Lock lock = new ReentrantLock();

    private Condition notEmpty = lock.newCondition();

    private Condition notFull = lock.newCondition();

    public BoundedQueue(int size){
        items = (E[]) new Object[size];
    }

    /**
     *
     * 入队，如果达到数组最大长度，调用put方法的线程阻塞等待，直到队列中有元素后会被唤醒
     * @param e
     * @throws InterruptedException
     */
    public void put(E e) throws InterruptedException {
        //1:锁住添加元素的代码块，保证操作的原子性
        lock.lock();
        try {
            //2:如果队列的元素数量已经达到数组的最大长度，让添加元素的线程释放锁，并进入notNull等待状态。
            while (count == items.length)
                notFull.await();
            items[addIndex] = e;
            //3：因为是个FIFO队列，所以此时队列满了，可以下一回合再从头开始放（因为头上的元素肯定被take掉了）
            if(++addIndex == items.length)
                addIndex = 0;
            ++count;
            //4:唤醒一个在notEmpty等待的线程（因为队列没有元素，去take的时候被置入等待状态的）
            notEmpty.signal();
        } finally {
            //5:释放锁
            lock.unlock();
        }
    }

    public E take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                notEmpty.await();
            Object o = items[removeIndex];
            items[removeIndex] = null;
            if (++removeIndex == items.length)
                removeIndex = 0;
            --count;
            notFull.signal();
            return (E) o;
        } finally {
            lock.unlock();
        }
    }


    /**
     *
     * 入队，如果到达数组最大长度返回false,否则返回true
     * @param e
     * @return
     */
    public boolean offer(E e){
        if (e == null)
            throw new NullPointerException();
        try {
            lock.lock();
            if (count == items.length)
                return false;
            items[addIndex] = e;
            if(++addIndex == items.length)
                addIndex = 0;
            ++count;
            notEmpty.signal();
            return true;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 入队操作，如果队列满了，会等待timeout段时间，若超时，则唤醒等待的线程，如果等待的时候未超时，就被其他线程唤醒，
     * 被唤醒后它会继续入队操作，如果队列还是满的。。那么就会返回false~~（因为是用for(;;)做了轮询嘛）
     * timeout的单位由TimeUnit来决定
     * @param e
     * @param timeout  等待时间
     * @param timeUnit Time的单位
     * @return
     * @throws InterruptedException
     */
    public boolean offer(E e, long timeout, TimeUnit timeUnit) throws InterruptedException {
        if (e == null)
            throw new NullPointerException();
        long naros = timeUnit.toNanos(timeout);
        lock.lock();

        try {
            //此处轮询，一旦等待的线程被唤醒，可以继续
            for (;;){
                //1:如果队列未满，执行入队操作
                if (count != items.length){
                    items[addIndex] = e;
                    if(++addIndex == items.length)
                        addIndex = 0;
                    ++count;
                    notEmpty.signal();
                    return true;
                }

                //3:如果naros < 0 ，则说明等待的线程已被唤醒，但是队列此时又满了，那么返回false
                if (naros < 0){
                    return false;
                }
                try {
                    //2:如果超时或者被其他线程唤醒，在notFull等待的线程会被唤醒，naros如果超时是0，否则是剩余时间 timeout - 消耗的时间
                    naros = notFull.awaitNanos(naros);
                } catch (InterruptedException e1) {
                    notFull.signal();
                    throw e1;
                }
            }
        } finally {
            lock.unlock();
        }
    }


    /**
     *
     * 出队，如果队列为空，则返回null,否则返回出队的元素
     * @return
     */
    public E poll(){
        lock.lock();
        try {
            while (count == 0)
                return null;
            Object o = items[removeIndex];
            items[removeIndex] = null;
            if (++removeIndex == items.length)
                removeIndex = 0;
            --count;
            notFull.signal();
            return (E) o;
        } finally {
            lock.unlock();
        }
    }

}
