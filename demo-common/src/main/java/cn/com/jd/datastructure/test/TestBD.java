package cn.com.jd.datastructure.test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by jihaixiao on 2016/12/8.
 */
public class TestBD {

    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        lock.lock();
        lock.lock();
        lock.lock();
    }
}
