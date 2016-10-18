package basic;

/**
 * lambda expression features in JDK 8.
 */
public class LambdaExpression {
    public static void main(String[] args) {
        Runnable runnable1 = new Runnable() {
            public void run() {
                System.out.println("Hello World One");
            }
        };
        Runnable runnable2 = ()-> System.out.println("Hello World Two");
        runnable1.run();
        runnable2.run();
    }
}