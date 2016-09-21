package cn.com.jd.test.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by jihaixiao on 2016/9/20.
 */
public class SemaphoreTest {

    private static final int THREAD_COUNT = 30;

    private static ExecutorService pool = Executors.newFixedThreadPool(THREAD_COUNT);

    private static Semaphore s = new Semaphore(10);

    public static void main(String[] args) {
        try {
            for (int i = 0; i< THREAD_COUNT;i++){
                pool.execute(new Runner());
            }
        } finally {
            pool.shutdown();
        }
    }

    static class Runner implements Runnable{

        @Override
        public void run() {
            try {
                s.acquire();
                Thread.sleep(2000);
                System.out.println("save success");
                s.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
