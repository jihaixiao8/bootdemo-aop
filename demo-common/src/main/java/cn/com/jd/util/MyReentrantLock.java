package cn.com.jd.util;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

/**
 * Created by jihaixiao on 2016/12/22.
 */
public class MyReentrantLock implements Lock{

    private final Sync sync;

    public MyReentrantLock() {
        this.sync = new NoFairSync();
    }

    abstract static class Sync extends java.util.concurrent.locks.AbstractQueuedSynchronizer{


        abstract void lock();


        /**
         *
         * 非公平锁，尝试修改同步状态，修改成功 返回true,失败返回false
         *
         *
         * @param acquires
         * @return
         */
        final boolean nonfairTryAcquire(int acquires){
            final Thread current = Thread.currentThread();

            //1:获取同步状态，该状态是volatile的，能够确保读到的是最新的

            int c = getState();
            if (c == 0){
                //2:如果同步状态是0，表示此时可以去获取锁，对同步状态的修改必须使用CAS操作
                if (compareAndSetState(0,acquires)){
                    //如果CAS修改状态成功，表示获取到了锁
                    setExclusiveOwnerThread(current);
                    return true;
                }
            } else if (current == getExclusiveOwnerThread()){
                //3：如果当前线程已经获得了锁,属于重入，累加一下状态
                int nextc = c + acquires;
                if (nextc < 0){
                    throw new Error("Maximum lock count exceeded");
                }
                //4:修改同步状态，有volatile语义
                setState(nextc);
                return true;
            }
            return false;
        }
    }

    static final class NoFairSync extends Sync{

        @Override
        final void lock() {
            if (compareAndSetState(0,1)){
                //如果成功修改同步状态，就把当前持有锁的线程修改为当前线程，表示获取到了锁
                setExclusiveOwnerThread(Thread.currentThread());
            } else {
                //如果没有修改成功，就调用AQS的acquire方法
                acquire(1);
            }
        }

        @Override
        protected final boolean tryAcquire(int acquires) {
            return nonfairTryAcquire(acquires);
        }
    }


    @Override
    public void lock() {
        sync.lock();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
