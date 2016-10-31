package me.hengwei.t.java.annotations;

import java.lang.reflect.Method;

import static java.lang.System.out;

/**
 * Run time annotation can be invocation directly by reflection api. Basically,
 * a Method.isAnnotationPresent() can find a method is annotation presented.
 */
public class TestMain {
    public static void main(String[] args) throws Exception {
        int passed = 0, failed = 0;
        for (Method m : Class.forName(args[0]).getMethods()) {
            if (m.isAnnotationPresent(Test.class)) {
                try {
                    m.invoke(null);
                    passed++;
                } catch (Throwable ex) {
                    out.printf(
                            "Test %s failed: %s %n", m, ex.getCause());
                    failed++;
                }
            }
        }
        out.printf("Passed: %d, Failed: %d%n", passed, failed);
    }
}
