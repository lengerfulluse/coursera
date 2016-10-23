package me.hengwei.t.javaee.jmx;

/**
 * Created by hengwei on 23/10/2016.
 */
public interface HelloMBean {
    void setMessage(String message);
    String getMessage();
    void sayHello();
}
