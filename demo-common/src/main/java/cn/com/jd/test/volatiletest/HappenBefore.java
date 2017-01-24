package cn.com.jd.test.volatiletest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by jihaixiao on 2016/11/7.
 */
public class HappenBefore {

    static  int y;

    static  int x;

    static int i;

    static int j;

    static ReentrantLock lock = new ReentrantLock();

    static CountDownLatch count = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        Athread athread = new Athread();
        Bthread bthread = new Bthread();
        athread.start();
        bthread.start();
        count.await();
//        Thread.sleep(2000);
        System.out.println(j+"--"+i+"--x:"+x+"--y:"+y);

    }


    static class Athread extends Thread{
        @Override
        public void run() {
            System.out.println("run---A--"+i+"--"+j);
            y=1;
            lock.lock();
            x=1;
            lock.unlock();
            System.out.println("end---A--x:"+x+"--y:"+y);
//            count.countDown();
        }
    }

    static class Bthread extends Thread{
        @Override
        public void run() {
            j=3;
            System.out.println("run---B--x:"+ x +"--y:"+y);
            lock.lock();
            i = 3;
            lock.unlock();
//            j=y;
            System.out.println("end---B--"+ x +"--"+y);
//            count.countDown();
        }
    }

}
