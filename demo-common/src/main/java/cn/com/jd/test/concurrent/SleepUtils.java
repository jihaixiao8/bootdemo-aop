package cn.com.jd.test.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * Created by jihaixiao on 2017/1/9.
 */
public class SleepUtils {

    public static final void second(long seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
