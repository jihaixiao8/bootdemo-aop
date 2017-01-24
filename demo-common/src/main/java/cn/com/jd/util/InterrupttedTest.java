package cn.com.jd.util;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by jihaixiao on 2016/12/22.
 */
public class InterrupttedTest {

    public static void main(String[] args) {
        InterrupttedTest test = new InterrupttedTest();
        System.out.println(test.getPark());
    }


    static class IThread extends Thread{


        @Override
        public void run() {
            System.out.println(Thread.interrupted()+".."+Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public boolean getPark(){
        LockSupport.park(this);
        return false;
    }
}
