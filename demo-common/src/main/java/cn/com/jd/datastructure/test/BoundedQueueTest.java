package cn.com.jd.datastructure.test;

import cn.com.jd.datastructure.BoundedQueue;

import java.util.concurrent.TimeUnit;

/**
 * Created by jihaixiao on 2016/9/8.
 */
public class BoundedQueueTest {

    public static void main(String[] args) throws InterruptedException {
        final BoundedQueue<Integer> queue = new BoundedQueue<Integer>(10);

        for (int i = 0; i< 11;i++){
            Thread t = new Thread(new Producer(queue,i));
            t.start();
        }

//        for (int i = 0; i< 10;i++){
//            Thread t = new Thread(new Consumer(queue));
//            t.start();
//        }

    }

    static class Producer implements Runnable{

        private BoundedQueue<Integer> queue;

        private int value;

        public Producer(BoundedQueue<Integer> queue, int value) {
            this.queue = queue;
            this.value = value;
        }

        @Override
        public void run() {
            try {
                boolean timeout =queue.offer(value,5000, TimeUnit.MILLISECONDS);
                System.out.println(timeout);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Consumer implements Runnable{

        private BoundedQueue<Integer> queue;

        public Consumer(BoundedQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                int m =queue.take();
                System.out.println(m);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
