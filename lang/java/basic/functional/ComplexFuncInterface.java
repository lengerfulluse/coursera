package basic.functional;

import sun.jvm.hotspot.ui.tree.SimpleTreeNode;

/**
 * Created by hengwei on 18/10/2016.
 */
@FunctionalInterface
public interface ComplexFuncInterface extends SampleFuncInterface {
    /**
     * in case the Interface it is extending in functional and it doesn’t declare
     * any new abstract methods then the new interface is also functional.
     */
    default public void doSomeWork() {
        System.out.println();
    }
}
