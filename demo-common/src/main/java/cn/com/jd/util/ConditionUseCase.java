package cn.com.jd.util;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by jihaixiao on 2017/1/20.
 */
public class ConditionUseCase {

    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();


    public static void main(String[] args) throws InterruptedException {
        //1:启动A线程，然后让A线程获取到锁，调用await()方法，释放锁，然后当前线程进入等待状态。
        AThread aThread = new AThread();
        aThread.start();
//        //2:睡1秒，确保A线程肯定现在B线程之前启动。
        Thread.sleep(1000);
//        //3:启动B线程，让B线程获取到锁，然后调用sigal()方法，去唤醒在等待队列中的线程（此处只有A调用了await()，所以只有A进入等待队列，等待被唤醒）
//        BThread bThread = new BThread();
//        bThread.start();
        System.out.println("走不走");
    }

    /**
     * A线程，在获取到锁以后会进入等待状态
     */
    static class AThread extends Thread{

        private volatile boolean flag = false;

        @Override
        public void run() {
            //注意:一定要先获取锁，不然会报错
            lock.lock();
            System.out.println("开始执行啦A"+Thread.currentThread().getName());
            try {
                condition.await();
                System.out.println("结束执行了A"+Thread.currentThread().getName());
            } catch (InterruptedException e) {
                System.out.println("任务A被中断");
            } finally {
                lock.unlock();
            }

        }


    }

    /**
     * B线程，用来在获取到锁以后，将A线程唤醒。
     */
    static class BThread extends Thread {
        @Override
        public void run() {

            try {
                //注意:一定要先获取锁，不然会报错
                lock.lock();
                System.out.println("开始执行B了");
                condition.signal();
            } finally {
                lock.unlock();
            }
            System.out.println("结束执行B了");

        }
    }

}
