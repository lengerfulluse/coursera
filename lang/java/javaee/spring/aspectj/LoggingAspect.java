package me.hengwei.t.javaee.spring.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;


/**
 * https://www.mkyong.com/spring3/spring-aop-aspectj-annotation-example/.
 */

@Aspect
public class LoggingAspect {

    @Before("execution(* me.hengwei.t.javaee.spring.aspectj.Customer.addCustomer(..))")
    public void logBefore(JoinPoint joinPoint) {
        //...
    }

    //@After("execution(* me.hengwei.t.javaee.spring.aspectj.Customer.addCustomer(..))")
    public void logAfter(JoinPoint joinPoint) {

        System.out.println("logAfter() is running!");
        System.out.println("hijacked : " + joinPoint.getSignature().getName());
        System.out.println("******");

    }

    //@AfterReturning(
            //pointcut = "execution(* me.hengwei.t.javaee.spring.aspectj.Customer.addCustomerReturnValue(..))",
            //returning= "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {

        System.out.println("logAfterReturning() is running!");
        System.out.println("hijacked : " + joinPoint.getSignature().getName());
        System.out.println("Method returned value is : " + result);
        System.out.println("******");
    }

    @Around("execution(* me.hengwei.t.javaee.spring.aspectj.Customer.addCustomerAround(..))")
    public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("logAround() is running!");
        System.out.println("hijacked method : " + joinPoint.getSignature().getName());
        System.out.println("hijacked arguments : " + Arrays.toString(joinPoint.getArgs()));

        System.out.println("Around before is running!");
        joinPoint.proceed(); //continue on the intercepted method
        System.out.println("Around after is running!");

        System.out.println("******");
    }

    @AfterThrowing(
            pointcut = "execution(* me.hengwei.t.javaee.spring.aspectj.Customer.addCustomerThrowException(..))",
            throwing= "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        //...
    }
}
