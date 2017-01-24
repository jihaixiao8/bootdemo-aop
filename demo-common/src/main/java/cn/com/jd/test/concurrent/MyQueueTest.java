package cn.com.jd.test.concurrent;

import cn.com.jd.util.MyArrayBlockingQueue;

/**
 * Created by jihaixiao on 2017/1/24.
 */
public class MyQueueTest {

    static MyArrayBlockingQueue queue = new MyArrayBlockingQueue(5);

    public static void main(String[] args) throws InterruptedException {
        for (int i=0;i<6;i++){
            AddThread addThread = new AddThread(i);
            addThread.start();
        }
        Thread.sleep(1000);
        RemoveThread removeThread = new RemoveThread();
        removeThread.start();
        Thread.sleep(1000);
        System.out.println(queue);
    }

    static class AddThread extends Thread {

        private Object o;

        public AddThread(Object o) {
            this.o = o;
        }

        @Override
        public void run() {
            try {
                queue.add(o);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    static class RemoveThread extends Thread {

        @Override
        public void run() {
            try {
                queue.remove();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
