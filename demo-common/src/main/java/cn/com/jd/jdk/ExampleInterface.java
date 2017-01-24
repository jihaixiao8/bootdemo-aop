package cn.com.jd.jdk;

/**
 * Created by jihaixiao on 2017/1/16.
 */
public interface ExampleInterface {

    void add();

    default void addOther(){
        System.out.println("add other。。。。");
    }

}
