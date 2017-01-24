package cn.com.jd.test.concurrent;

/**
 * Created by jihaixiao on 2017/1/9.
 */
public class ThreadState {

    protected  String a;

    public static void main(String[] args) {
//        new Thread(new TimeWaiting(),"TimeWaitingThread").start();
//        new Thread(new Waiting(),"waitingThread").start();
//        new Thread(new Blocked(),"blockedthrea-1").start();
//        new Thread(new Blocked(),"blockedthrea-2").start();

    }

     class TimeWaiting implements Runnable{

        @Override
        public void run() {
            while (true){
                SleepUtils.second(100);
                System.out.println(a);
            }
        }
    }

     class Waiting implements Runnable{

        @Override
        public void run() {
            while (true){
                synchronized (Waiting.class){
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class Blocked implements Runnable{

        @Override
        public void run() {
            synchronized (Blocked.class){
                while (true) {
                    SleepUtils.second(100);
                }
            }
        }
    }

}
