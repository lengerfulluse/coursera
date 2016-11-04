package me.hengwei.t.java.syntax;

/**
 * All static synchronized method share Class lock. All non-static
 * synchronized method share object lock. Different instants share
 * different lock.
 */
public class StaticSynchronized {
    synchronized public  static void m1() {
        System.out.println("static sync method 1");
        try {
            Thread.sleep(4000);
            System.out.println("now:" + System.currentTimeMillis()/1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    synchronized public static void m2() {
        System.out.println("static sync method 2");
        try {
            Thread.sleep(4000);
            System.out.println("now:" + System.currentTimeMillis()/1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    synchronized public void m3() {
        System.out.println("non static sync method 3");
        try {
            Thread.sleep(4000);
            System.out.println("now:" + System.currentTimeMillis()/1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    synchronized public void m4() {
        System.out.println("non static sync method 4");
        try {
            Thread.sleep(4000);
            System.out.println("now:" + System.currentTimeMillis()/1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        StaticSynchronized staticSynchronized1 = new StaticSynchronized();
        StaticSynchronized staticSynchronized2 = new StaticSynchronized();
        Thread thread1 = new Thread(()->{
            staticSynchronized1.m3();
        });
        Thread thread2 = new Thread(()-> {
            staticSynchronized1.m4();
        });

        Thread thread3 = new Thread(()->{
            StaticSynchronized.m1();
        });
        Thread thread4 = new Thread(()->{
            StaticSynchronized.m2();
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
