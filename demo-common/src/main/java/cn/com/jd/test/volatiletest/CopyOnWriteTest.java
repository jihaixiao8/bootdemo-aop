package cn.com.jd.test.volatiletest;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * Created by jihaixiao on 2016/11/8.
 */
public class CopyOnWriteTest {

    static List<String> list = new CopyOnWriteArrayList<String>();

    static CountDownLatch count = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
        AThread a = new AThread();
        BThread b = new BThread();
        a.start();
        b.start();
    }


    static class AThread extends Thread{
        @Override
        public void run() {
            System.out.println("走A");
            count.countDown();
        }
    }

    static class BThread extends Thread{
        @Override
        public void run() {
            try {
                count.await();
                System.out.println("走B");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
