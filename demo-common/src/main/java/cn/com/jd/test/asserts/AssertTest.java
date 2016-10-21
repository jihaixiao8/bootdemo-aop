package cn.com.jd.test.asserts;

import cn.com.jd.util.Assert;

/**
 * Created by jihaixiao on 2016/10/19.
 */
public class AssertTest {

    public static void main(String[] args) {
        Assert.notNull(null,"obj is null[exception]");
        System.out.println("hello world");
    }

}
