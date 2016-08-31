package cn.com.jd.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by root on 16-8-31.
 */
public class HelloWorldAspect {

    public void beforeAdvice(String params){
        System.out.println("---------before advice---------"+params);
    }

    public void afterFinallyAdvice(){
        System.out.println("--------------after finally advice");
    }

    public void afterReturningAdvice(Object o){
        System.out.println("----after returning advice returnval:"+o);
    }

    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("----around before advice--");
        Object retVal = proceedingJoinPoint.proceed(new Object[]{"replace"});
        System.out.println("--------around after advice-----------");
        return retVal;
    }

}
