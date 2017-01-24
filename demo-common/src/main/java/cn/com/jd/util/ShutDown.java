package cn.com.jd.util;

import java.util.concurrent.TimeUnit;

/**
 * Created by jihaixiao on 2017/1/13.
 */
public class ShutDown {

    public static void main(String[] args) throws InterruptedException {
        Runner runner = new Runner();
        Thread countThread = new Thread(runner,"countThread");
        countThread.start();
        TimeUnit.SECONDS.sleep(1);
        countThread.interrupt();
        Runner two = new Runner();
        countThread = new Thread(two,"CountThread");
        countThread.start();
        TimeUnit.SECONDS.sleep(1);
        two.cancel();
    }

    private static class Runner implements Runnable{

        private long i;

        private volatile boolean on = true;

        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println("Count i="+i);
        }

        public void cancel(){
            on = false;
        }
    }

}
