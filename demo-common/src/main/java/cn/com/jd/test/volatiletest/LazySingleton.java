package cn.com.jd.test.volatiletest;

import java.util.Random;

/**
 * Created by jihaixiao on 2016/11/3.
 */
public class LazySingleton {

    private int someField;

    private static volatile LazySingleton instance;

    private LazySingleton(){
        this.someField = new Random().nextInt(200)+1;
    }

    public static LazySingleton getInstance(){
        if (instance == null){
            synchronized (LazySingleton.class){
                if (instance == null){
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }

}
