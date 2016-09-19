package cn.com.jd.test.volatiletest;

/**
 * Created by jihaixiao on 2016/9/19.
 */
public class VolatileExample extends Thread{

    private static boolean flag = false;

    @Override
    public void run() {
        while (!flag){}
    }


    public static void main(String[] args) throws InterruptedException {
        new VolatileExample().start();
        Thread.sleep(100);
        flag = true;
    }
}
