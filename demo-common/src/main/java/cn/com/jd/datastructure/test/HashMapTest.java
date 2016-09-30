package cn.com.jd.datastructure.test;

import cn.com.jd.datastructure.HashMap;
import cn.com.jd.test.volatiletest.Person;

import java.util.Map;

/**
 * Created by jihaixiao on 2016/9/30.
 */
public class HashMapTest {

    public static void main(String[] args) {
        HashMap<Person,String> map = new HashMap<Person,String>();
//        Map<Person,String> map = new java.util.HashMap<Person, String>();
        Person p1 = new Person();
        Person p2 = new Person();
        map.put(p1,"abc");
        map.put(p2,"cdb");
        System.out.println(map.get(p1));
    }
}
