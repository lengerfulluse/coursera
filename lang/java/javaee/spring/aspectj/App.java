package me.hengwei.t.javaee.spring.aspectj;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by hengwei on 26/10/2016.
 */
public class App {
    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring-Customer.xml");
        Customer customer = (Customer) applicationContext.getBean("customer");
        customer.addCustomer();
        //customer.addCustomerAround("weiheng around");
        customer.addCustomerReturnValue();
        //customer.addCustomerThrowException();
    }
}
