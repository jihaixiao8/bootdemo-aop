package cn.com.jd.test.volatiletest;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by jihaixiao on 2016/11/18.
 */
public class ThreadPoolCallExecute {

    private static ExecutorService threadPool = Executors.newFixedThreadPool(5);

    private static CountDownLatch counter = new CountDownLatch(1);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

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
                Future<Integer> future = threadPool.submit(new Caller(12));
                System.out.println(future.get());
            }
        } finally {
            threadPool.shutdown();
        }

    }


    static class Caller implements Callable{

        private int param;

        public Caller(int param) {
            this.param = param;
        }


        @Override
        public Object call() throws Exception {
            counter.await();
            System.out.println("逻辑B");
            param = param + new Random().nextInt(20);

            double a = 1;               //A
            double b = 2;               //B
            double c = a * b;           //C

            return param;
        }
    }

}
