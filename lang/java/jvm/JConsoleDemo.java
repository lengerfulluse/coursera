package jvm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hengwei on 10/3/16.
 */
public class JConsoleDemo {
    public byte[] placeHolder = new byte[64 * 1024];


    public static void fillHeap(int num) throws InterruptedException {
        List<JConsoleDemo> list = new ArrayList<JConsoleDemo>();
        for (int i=0; i < num; i++) {
            Thread.sleep(50);
            list.add(new JConsoleDemo());
        }
        System.gc();
    }


    /**
     * create busy thread demo.
     */
    public static void createBusyThread() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                while(true)
                    ;
            }
        }, "testBusyThread");
        thread.start();
    }

    public static void createLockThread(final Object lock) {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "testLockThread");
        thread.start();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        JConsoleDemo.createBusyThread();
        br.readLine();
        JConsoleDemo.createLockThread(new Object());
    }
}
