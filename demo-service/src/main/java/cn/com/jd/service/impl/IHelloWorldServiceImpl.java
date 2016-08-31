package cn.com.jd.service.impl;

import cn.com.jd.service.IHelloWorldService;

/**
 * Created by root on 16-8-31.
 */
public class IHelloWorldServiceImpl implements IHelloWorldService{
    @Override
    public void sayHello() {
        System.out.println("------------hello world!!-----------");
    }

    @Override
    public void getEntity() {
        System.out.println("-------------get entity------------");
    }

    @Override
    public void sayBefore(String param) {
        System.out.println("---say----"+param);
    }

    @Override
    public boolean sayAfterReturning() {
        System.out.println("=============after returning");
        return true;
    }

    @Override
    public void sayAround(String param) {
        System.out.println("-----------around param----------------"+param);
    }

    @Override
    public void sayHelloWithAnno() {
        System.out.println("-----------sayHelloWithAnno----------");
    }
}
