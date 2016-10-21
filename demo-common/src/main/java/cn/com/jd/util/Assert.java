package cn.com.jd.util;

/**
 * Created by jihaixiao on 2016/10/19.
 */
public class Assert {

    public static void notNull(Object object,String message){
        if (object == null)
            throw new IllegalArgumentException(message);
    }

}
