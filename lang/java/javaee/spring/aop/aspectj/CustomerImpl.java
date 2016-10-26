package me.hengwei.t.javaee.spring.aspectj;

/**
 * Created by hengwei on 26/10/2016.
 */
public class CustomerImpl implements Customer {
    public void addCustomer(){
        System.out.println("addCustomer() is running ");
    }

    public String addCustomerReturnValue(){
        System.out.println("addCustomerReturnValue() is running ");
        return "abc";
    }

    public void addCustomerThrowException() throws Exception {
        System.out.println("addCustomerThrowException() is running ");
        throw new Exception("Generic Error");
    }

    public void addCustomerAround(String name){
        System.out.println("addCustomerAround() is running, args : " + name);
    }
}
