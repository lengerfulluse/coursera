package jvm;

/**
 * -Xss2M
 */
public class JavaVMStackOutOfMemoryException {
    private void dontStop() {
        while(true) {

        }
    }
    public void stackLeakByThread() {
        while(true) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOutOfMemoryException stackOutOfMemoryException = new JavaVMStackOutOfMemoryException();
        stackOutOfMemoryException.stackLeakByThread();
    }
}
