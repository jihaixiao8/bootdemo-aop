package cn.com.jd.test.volatiletest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jihaixiao on 2016/9/23.
 */
public class Test {

    public static void main(String[] args) {
        int a = Integer.parseInt("110",2);
        System.out.println(a ^ (a >>> 16));
        int b = Integer.parseInt("10",2);
        System.out.println(b);
    }

    public static void plus(AtomicInteger count){
        count.incrementAndGet();
    }

}
