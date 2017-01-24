package cn.com.jd.test.concurrent;

import java.util.Random;

/**
 * Created by jihaixiao on 2016/10/21.
 */
public class StringBuilderTest {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
//        StringBuffer sb = new StringBuf:ss: fer();
        StringBuilder sb = new StringBuilder();

        for (int i=0;i<10000000;i++){
            sb.append("abc");
        }
        System.out.println("耗时："+(System.currentTimeMillis() - start)+"ms");
    }

}
