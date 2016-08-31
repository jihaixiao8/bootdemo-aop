package cn.com.jd.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * Created by root on 16-8-31.
 */
@Aspect
public class PerformanceAspect {

    /**
     *
     */
    @Pointcut(value = "execution(* cn.com.jd.service..*.*(..))")
    public void pointcutAnno(){}

    @Around(value = "execution(* cn.com.jd.service..*.sayHelloWithAnno(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("method start---"+start);
        proceedingJoinPoint.proceed();
        System.out.println("method end--- used"+(System.currentTimeMillis() - start)+"ms");
        return null;
    }

    @Before(value = "pointcutAnno()")
    public void beforeAdvice(){
        System.out.println("----------anno before----------------");
    }

    @After(value = "execution(* cn.com.jd.service..*.getEntity(..))")
    public void afterFinallyAdvice(){
        System.out.println("-----------anno after finally-------------");
    }
}
