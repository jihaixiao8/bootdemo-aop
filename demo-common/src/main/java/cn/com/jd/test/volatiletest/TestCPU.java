package cn.com.jd.test.volatiletest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Created by jihaixiao on 2016/11/7.
 */
public class TestCPU {

    static  int a;

    static  int b;

    static int x;

    static int y;

    static CountDownLatch count = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
//        Athread a = new Athread();
//        Bthread b = new Bthread();
//        a.start();
//        b.start();
//        count.await();
//        System.out.println(x+"--"+y);
        int count = 0;
        for (int i=0;i<10;i++){
            count = count++;
        }
        System.out.println(count);
        Map<String,Object> map = new HashMap<String,Object>();
    }




    static class Athread extends Thread{
        @Override
        public void run() {
            a=1;
            x=b;
            count.countDown();
        }
    }

    static class Bthread extends Thread{
        @Override
        public void run() {
            b=2;
            y=a;
            count.countDown();
        }
    }

}
