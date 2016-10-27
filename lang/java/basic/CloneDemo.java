package me.hengwei.t.java;

import lombok.Getter;
import lombok.Setter;
import me.hengwei.t.javaee.mina.MinaTimeClient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * In book of "Effective Java", Josh Bloch recommend using a copying instead of implementing
 * Cloneable and write clone method.
 * http://www.artima.com/intv/bloch13.html.
 */

public class CloneDemo implements Cloneable {
    @Setter @Getter
    int age = 0;
    @Setter
    String name = "Dolly";
    @Getter
    List<String> list = new ArrayList<String>();

    @Override
    public Object clone() throws CloneNotSupportedException {
        /* clone method is broken */
        CloneDemo cloneDemo = (CloneDemo)super.clone();
        /* primitive type clone directly */
        cloneDemo.age = 1;
        cloneDemo.name = "Dolly";
        /* mutable type should use defensive copying */
        List<SomeObject> listCopy = new ArrayList<SomeObject>();
        Iterator<SomeObject> listIter = list.iterator();
        while(listIter.hasNext()) {
            listCopy.add(listIter.next().clone());
        }
        return cloneDemo;
    }

    public static void main(String[] args) {
        CloneDemo original = new CloneDemo();
        original.setAge(1);
        try {
            // We clone the baby.
            CloneDemo clonedBaby = (CloneDemo) original.clone();
            // both babies now have: age 1, name "Molly" and an empty arraylist
            original.setAge(2);
            // originalBaby has age 2, the clone has age 1
            original.setName("Molly");
            // same goes for the String, both are individual fields
            original.getList().add(new SomeObject());
            // both babies get the string added to the list,
            // because both point to the same list.
        }
        catch (CloneNotSupportedException e) {}
    }
}
