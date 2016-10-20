package basic.concurrency;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * demonstrate ConcurrentMap basic operations.
 */
public class ConcurrentMapDemo {

    public void conCurrentMap() {
        ConcurrentMap<String, String> map =  new ConcurrentHashMap<String, String>() {{
            put("foo", "bar");
            put("han", "solo");
            put("r2", "d2");
            put("c3", "p0");
        }};
        /* it's really cool to iterate collection or map via forEach with lambda expression. */
        map.forEach((key, value)-> System.out.println(key + "->" + value));
        map.putIfAbsent("foo", "no");

        /* compute can transform a single entry. */
        map.compute("foo", (key, value)-> key+value);
        System.out.println(map.get("foo"));

        /* merge unify a new value with an existing value in the map. */
        map.merge("foo", "newbar", (oldValue, newValue)-> oldValue + newValue);
        System.out.println(map.get("foo"));
    }

    public void conCurrentHashMap() {
        ConcurrentHashMap<String, String> map =  new ConcurrentHashMap<String, String>() {{
            put("foo", "bar");
            put("han", "solo");
            put("r2", "d2");
            put("c3", "p0");
        }};
        map.forEach(1, (key, value)-> System.out.printf("key: %s; value: %s; thread: %s\n",
                key, value, Thread.currentThread().getName()));

        /* search example with concurrent thread */
        String result = map.search(1, (key, value) -> {
            System.out.println(Thread.currentThread().getName());
            if ("foo".equals(key)) {
                return value;
            }
            return null;
        });
        System.out.println("Search Result: " + result);

        /* map reduce example */
        result = map.reduce(1, (key, value)-> key, (s1, s2)-> s1 + "," + s2);
        System.out.println("Reduce Result: " + result);

    }
    public static void main(String[] args) {
        ConcurrentMapDemo concurrentMapDemo = new ConcurrentMapDemo();
        //concurrentMapDemo.conCurrentMap();
        concurrentMapDemo.conCurrentHashMap();

    }
}
