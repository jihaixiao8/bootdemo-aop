package cn.com.jd.test.volatiletest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jihaixiao on 2016/11/18.
 */
public class ThreadPoolExecute {

    private static ExecutorService threadPool = Executors.newFixedThreadPool(5);

    private static CountDownLatch counter = new CountDownLatch(1);

    public static void main(String[] args) {

        try {
            Thread A = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("逻辑A");
                    counter.countDown();
                }
            });
            A.start();

            for (int i=0;i<5;i++){
                threadPool.execute(new Runner(12));
            }
        } finally {
            threadPool.shutdown();
        }

    }


    static class Runner implements Runnable{

        private int param;

        public Runner(int param) {
            this.param = param;
        }

        @Override
        public void run() {
            try {
                counter.await();

                //跑你的线程池的逻辑
                System.out.println("逻辑B"+param);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
