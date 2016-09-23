package cn.com.jd.test.volatiletest;

/**
 * Created by jihaixiao on 2016/9/19.
 */
public class VolatileExample extends Thread{

    private static boolean flag = false;

    @Override
    public void run() {
        while (!flag){
            Object o = new Object();
        }

//        for (;;){
//            if (!flag){
//                Object o = new Object();
//            }else {
//                break;
//            }
//
//        }

    }


    public static void main(String[] args) throws InterruptedException {
        new VolatileExample().start();
        Thread.sleep(100);
        flag = true;
    }
}
