package cn.com.jd.service;

/**
 * Created by root on 16-8-31.
 */
public interface IHelloWorldService {

    void sayHello();

    void getEntity();

    void sayBefore(String param);

    boolean sayAfterReturning();

    void sayAround(String param);

    void sayHelloWithAnno();

}
