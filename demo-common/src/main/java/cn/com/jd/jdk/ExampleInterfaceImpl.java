package cn.com.jd.jdk;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by jihaixiao on 2017/1/16.
 */
public class ExampleInterfaceImpl implements ExampleInterface{

    static final int NCPU = Runtime.getRuntime().availableProcessors();

    @Override
    public void add() {
        System.out.println(256 >>> 1);
        System.out.println("add....");
        ConcurrentHashMap<String,String> map = new ConcurrentHashMap<>(11);
        HashMap<String,String> hashMap = new HashMap<>();
        System.out.println(NCPU);
    }

    public static void main(String[] args) {
        ExampleInterfaceImpl exampleInterface = new ExampleInterfaceImpl();
        exampleInterface.add();
        exampleInterface.addOther();
    }
}
