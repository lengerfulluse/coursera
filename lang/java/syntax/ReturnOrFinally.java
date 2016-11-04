package me.hengwei.t.java.syntax;

/**
 * The finally block always executes when the try block exits. This ensures that the finally
 * block is executed even if an unexpected occurs. But finally is useful for more than just
 * exception handling — it allows the programmer to avoid having cleanup code accidentally
 * bypassed by a return, continue, or break. Putting cleanup code in a finally block is
 * always a good practice, even when no exceptions are anticipated.
 */
public class ReturnOrFinally {
    /* OUTPUT:
     * finally after return...true
     * false
     */
    public static boolean finallyAfterReturn() {
        boolean flag = false;
        try {
            return flag;
        } finally {
            flag = true;
            System.out.println("finally after return..." + flag);
        }
    }

    /* OUTPUT
     * finally with return...true
     * true
     */
    public static boolean finallyWithRetrun() {
        boolean flag = false;
        try {
            return flag;
        } finally {
            flag = true;
            System.out.println("finally with return..." + flag);
            return flag;
        }
    }

    public static void main(String[] args) {
        System.out.println(ReturnOrFinally.finallyAfterReturn());
        System.out.println(ReturnOrFinally.finallyWithRetrun());
    }
}
