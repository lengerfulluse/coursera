/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.hengwei.t.tutorial.javaee;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * JAX-WS service demo.
 * @author weiheng
 */
@WebService
public class HelloWebService {
    private String message = new String("Hello, ");
    public void HelloWebService() {
        
    }
    @WebMethod
    public String sayHello(String name) {
        return message + name + ".";
    }
}
