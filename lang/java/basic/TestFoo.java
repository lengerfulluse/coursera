package me.hengwei.t.java.annotations;

/**
 * Created by hengwei on 31/10/2016.
 */
public class TestFoo {
    @Test public static void m1() { }
    public static void m2() { }
    @Test public static void m3() {
        throw new RuntimeException("boom");
    }
}
