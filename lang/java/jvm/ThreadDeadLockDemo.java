package jvm;

/**
 * Demo for thread dead lock
 */
public class ThreadDeadLockDemo implements Runnable {
    int a, b;

    public ThreadDeadLockDemo(int a, int b) {
        this.a = a;
        this.b = b;
    }
    public void run() {
        synchronized (Integer.valueOf(a)) {
            synchronized (Integer.valueOf(b)) {
                System.out.println(a+b);
            }
        }
    }

    public static void main(String[] args) {
        for (int i=0; i< 1000; i++) {
            new Thread (new ThreadDeadLockDemo(1, 2)).start();
            new Thread (new ThreadDeadLockDemo(2, 1)).start();
        }
        Math.random();
    }
}
