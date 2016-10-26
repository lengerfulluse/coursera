package me.hengwei.t.javaee.spring.aspectj;

/**
 * Created by hengwei on 26/10/2016.
 */
public interface Customer {
    void addCustomer();

    String addCustomerReturnValue();

    void addCustomerThrowException() throws Exception;

    void addCustomerAround(String name);
}
