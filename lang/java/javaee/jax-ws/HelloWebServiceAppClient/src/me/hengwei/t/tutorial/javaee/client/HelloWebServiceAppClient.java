/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.hengwei.t.tutorial.javaee.client;

import me.hengwei.t.tutorial.javaee.HelloWebService;
import me.hengwei.t.tutorial.javaee.HelloWebServiceService;

/**
 * application client demo for JAX-WS service.
 * @author weiheng
 */
public class HelloWebServiceAppClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(sayHello("world"));
    }

    private static String sayHello(java.lang.String arg0) {
        HelloWebServiceService service = new HelloWebServiceService();
        HelloWebService port = service.getHelloWebServicePort();
        return port.sayHello(arg0);
    }
    
}
