package basic.lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by hengwei on 17/10/2016.
 */
public class ComparatorTest {
    class Person {
        private String givenName;
        private String surName;
        public String getSurName() {
            return this.surName;
        }
    }
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        // Comparator with inner class.
        Collections.sort(personList, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p1.getSurName().compareTo(p2.getSurName());
            }
        });

        // Comparator with lambda expression.
        // lambda expression declares the parameter type passed to the expression are optional.
        Collections.sort(personList, (p1, p2)-> p1.getSurName().compareTo(p2.getSurName()));
    }
}
