package basic.lambda;

/**
 * Created by hengwei on 17/10/2016.
 */
public class RunnableTest {
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
