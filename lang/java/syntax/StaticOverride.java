package me.hengwei.t.java.syntax;

/**
 * An instance method in a subclass with the same signature (name, plus the
 * number and the type of its parameters) and return type as an instance method
 * in the superclass overrides the superclass's method.
 *
 * If a subclass defines a static method with the same signature as a static
 * method in the superclass, then the method in the subclass hides the one in
 * the superclass.
 *
 * The distinction between hiding a static method and overriding an instance
 * method has important implications:
 * -> The version of the overridden instance method that gets invoked is the
 * one in the subclass.
 * -> The version of the hidden static method that gets invoked depends on
 * whether it is invoked from the superclass or the subclass.
 */
public class StaticOverride {
    /**
     * A nested class is a member of it's enclosing class. Non-static inner classes(inner class)
     * have access to other members of enclosing class, even if they are declared as private.
     * Static nested class do not have access to other members of the enclosing class. As a member
     * of the OuterClass, a nested class can be declared private, public, protected, or package
     * private. (Recall that outer classes can only be declared public or package private.)
     */
    static class Dog {
        public static void bark() {
            System.out.println("I am a dog, wang, wang..");
        }

        public void eat() {
            System.out.println("I am a dog, eat, eat..");
        }
    }

    static class Poodle extends Dog {
        public static void bark() {
            System.out.println("I am a poodle, wow, wow...");
        }
        public void eat() {
            System.out.println("I am a poodle, aha, aha...");
        }
    }

    public static void main(String[] args) {
        Dog poodle = new Poodle();
        poodle.bark(); /* I am a dog, wang, wang.. */
        poodle.eat(); /* I am a poodle, eat, eat.. */
    }
}
