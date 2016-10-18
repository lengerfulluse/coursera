package basic.functional;

/**
 * Functional interface demon
 */
@FunctionalInterface
public interface SampleFuncInterface {

    public void doWork();

    /**
     * The interface can also declare the abstract methods from the
     * java.lang.Object class, but still the interface can be called
     * as a Functional Interface:
     */
    public String toString();
    public boolean equals(Object o);

}
