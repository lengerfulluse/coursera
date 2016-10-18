package cache;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.BinaryConnectionFactory;
import net.spy.memcached.MemcachedClient;

import java.io.IOException;

/**
 * Simple memcached client invocation demo.
 */
public class MemcachedClientDemo {
    private static final String NAMESPACE= "SACHARYA:5d41402abc4b2a76b9719d91101";
    private static MemcachedClientDemo cacheInstance = null;
    private static MemcachedClient client = null;

    public MemcachedClientDemo() {
        try {
            client = new MemcachedClient(
                    new BinaryConnectionFactory(),
                    AddrUtil.getAddresses("127.0.0.1:11211")
            );
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized MemcachedClientDemo getInstance() {
        if (cacheInstance == null) {
            return new MemcachedClientDemo();
        } else {
            return cacheInstance;
        }
    }

    public void set(String key, int ttl, final Object o) {
        client.set(NAMESPACE + key, ttl, o);
    }

    public Object get(String key) {
        Object o = client.get(NAMESPACE + key);
        if(o == null) {
            System.out.println("Cache MISS for KEY: " + key);
        } else {
            System.out.println("Cache HIT for KEY: " + key);
        }
        return o;
    }

    public Object delete(String key) {
        return client.delete(NAMESPACE + key);
    }

    public static void main(String[] args) {
        MemcachedClientDemo memcachedClientDemo = MemcachedClientDemo.getInstance();
        memcachedClientDemo.set("joseph", 3600, "heng");
        memcachedClientDemo.get("joseph");
    }
}
