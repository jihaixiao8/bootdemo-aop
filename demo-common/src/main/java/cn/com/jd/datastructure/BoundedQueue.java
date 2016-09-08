package cn.com.jd.datastructure;

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
public class BoundedQueue<E> {

    private E[] items;

    private int count;

    private int addIndex;

    private int removeIndex;

    private Lock lock = new ReentrantLock();

    private Condition notEmpty = lock.newCondition();

    private Condition notNull = lock.newCondition();

    public BoundedQueue(int size){
        items = (E[]) new Object[size];
    }

    public void add(E e) throws InterruptedException {
        //1:锁住添加元素的代码块，保证操作的原子性
        lock.lock();
        try {
            //2:如果队列的元素数量已经达到数组的最大长度，让添加元素的线程释放锁，并进入notNull等待状态。
            while (count == items.length)
                    notNull.await();
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

    public E remove() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                notEmpty.await();
            Object o = items[removeIndex];
            if (++removeIndex == items.length)
                removeIndex = 0;
            --count;
            notNull.signal();
            return (E) o;
        } finally {
            lock.unlock();
        }
    }


}
