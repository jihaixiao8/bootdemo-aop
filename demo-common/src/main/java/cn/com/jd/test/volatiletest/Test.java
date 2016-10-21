package cn.com.jd.test.volatiletest;


import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jihaixiao on 2016/9/23.
 */
public class Test {

    public static void main(String[] args) {
        System.out.println(0x1 +0x2);
    }

    public static void plus(AtomicInteger count){
        count.incrementAndGet();
    }

}
