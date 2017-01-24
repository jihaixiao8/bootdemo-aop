package cn.com.jd.test.volatiletest;

import java.util.concurrent.CountDownLatch;

/**
 * Created by jihaixiao on 2016/9/19.
 */
public class VolatileExample{

    static int a =0;

    static volatile boolean flag = false;

    static CountDownLatch count = new CountDownLatch(1);

    static void write(){
        a = 1;
        flag = true;
    }

    static void reader(){
        if (flag){
            int i = a;
            System.out.println(i);
        } else{
            System.out.println(flag);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ABThread a = new ABThread();
        BThread b = new BThread();
        a.start();
        b.start();
    }

    static class ABThread extends Thread{
        @Override
        public void run() {
            System.out.println("write");
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            write();
            count.countDown();
        }
    }

    static class BThread extends Thread{
        @Override
        public void run() {
            try {
                count.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("read");
            reader();
        }
    }

}
