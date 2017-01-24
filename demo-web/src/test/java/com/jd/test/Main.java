package com.jd.test;

import com.jd.test.hah.RedisUtil;

/**
 * Created by jihaixiao on 2017/1/12.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(RedisUtil.getUrl());
    }

//    public void test(){
//        String url = this.getClass().getResource("").getPath();
////        String path = url.substring(0,url.indexOf("WEB-INF"))+"WEB-INF/common.properties";
//        System.out.println(url);
//    }

}
