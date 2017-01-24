package cn.com.jd.test.volatiletest;

import org.jboss.netty.util.internal.ConcurrentHashMap;

import java.util.Map;

/**
 * Created by jihaixiao on 2016/11/3.
 */
public class ConcurrentHashMapTest {

    public static void main(String[] args) {
        Map<String,Object> map = new ConcurrentHashMap<String,Object>();
        System.out.println(map);
    }

}
