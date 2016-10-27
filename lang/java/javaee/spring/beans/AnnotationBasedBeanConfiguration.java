package me.hengwei.t.javaee.spring.beans;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * AnnotationConfigApplicationContext demo.
 */
@ComponentScan({"me.hengwei.t.javaee.spring.beans"})
public class AnnotationBasedBeanConfiguration {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AnnotationBasedBeanConfiguration.class);
        ServiceBean serviceBean = (ServiceBean)applicationContext.getBean("serviceBean");
        serviceBean.setServiceName("componentscan demo");
        System.out.println(serviceBean.getServiceName());
    }

}
