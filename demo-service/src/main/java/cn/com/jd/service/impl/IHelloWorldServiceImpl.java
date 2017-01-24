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
    public void sayBefore(String parameter) {
        System.out.println("---say----"+parameter);
    }

    @Override
    public boolean sayAfterReturning() {
        System.out.println("=============after returning");
        return true;
    }

    @Override
    public void sayAround(String parameter) {
        System.out.println("-----------around parameter----------------"+parameter);
    }

    @Override
    public void sayHelloWithAnno() {
        System.out.println("-----------sayHelloWithAnno----------");
    }
}
