package cn.com.jd.test.volatiletest;

/**
 * Created by jihaixiao on 2016/11/10.
 */
public class PossibleReording {


    static int a=0,b=0;
    static int x=0,y=0;


    public static void test() throws InterruptedException {
        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                a = 1;
                x = b;
            }
        });

        Thread other = new Thread(new Runnable() {
            @Override
            public void run() {
                b = 1;
                y = a;
            }
        });
        one.start();
        other.start();
        one.join();
        other.join();
        System.out.println("("+x+","+y+")");
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i =0;i<10;i++){
            test();
        }
    }

}
