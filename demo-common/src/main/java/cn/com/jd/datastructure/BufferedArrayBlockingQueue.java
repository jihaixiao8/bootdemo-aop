package cn.com.jd.datastructure;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by jihaixiao on 2016/9/8.
 */
public class BufferedArrayBlockingQueue<E> {

    private E[] items;

    private ReentrantLock lock = new ReentrantLock();

    private Condition notFull = lock.newCondition();

    private Condition notEmpty = lock.newCondition();


}
