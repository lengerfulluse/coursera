package me.hengwei.t.java.syntax;

/**
 * Great demo to distinguish Java object reference, and reference copy in method
 * invocation.
 */
public class StackObjectLifeCycle {
    class Hello {
        public int var = 10;
    }

    public void aMethod() {
        Hello hello = new Hello();
        hello.var = 30;
        anotherMethod(hello, 90);
        System.out.println(hello.var);
    }

    /* here the hello reference is another copy of original hello reference */
    public void anotherMethod(Hello hello, int i) {
        i = 0;
        Hello vhello = new Hello();
        vhello.var = 20;
        hello = vhello;
        System.out.println(hello.var + " " +i);
    }

    public static void main(String[] args ) {
        StackObjectLifeCycle stackObjectLifeCycle = new StackObjectLifeCycle();
        stackObjectLifeCycle.aMethod();
        /* OUTPUT:
         * 20 0
         * 30
         */
    }
}
