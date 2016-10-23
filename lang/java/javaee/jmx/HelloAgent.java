package me.hengwei.t.javaee.jmx;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * http://alvinalexander.com/blog/post/java/source-code-java-jmx-hello-world-application
 */
public class HelloAgent {
    private MBeanServer mbs = null;

    public void registerJMX() {
        /* get platform MBeanServer */
        mbs = ManagementFactory.getPlatformMBeanServer();
        /* unique identification of MBeans. */
        Hello helloBean = new Hello();
        ObjectName helloName;
        try {
            /* Uniquely identify the MBeans and register them with the platform MBeanServer */
            helloName = new ObjectName("FOO:name=HelloBean");
            mbs.registerMBean(helloBean, helloName);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /* Utility method: so that the application continues to run */
    private static void waitForEnterPressed() {
        try {
            System.out.println("Press  to continue...");
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String argv[]) {
        HelloAgent agent = new HelloAgent();
        agent.registerJMX();
        System.out.println("HelloAgent is running...");
        HelloAgent.waitForEnterPressed();
    }
}
