package cn.com.jd.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by root on 16-9-1.
 */
public class AOPUtil {

    public static void main(String[] args) {
        long time = TimeUnit.SECONDS.toSeconds(10);
        System.out.println(time);
        if (time < 0){
            System.out.println("end");
        }
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
        queue.offer(1);
    }
}
