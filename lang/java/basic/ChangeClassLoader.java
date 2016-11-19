package me.hengwei.t.java;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Demo on change context class loader demo.
 * http://docs.oracle.com/javase/jndi/tutorial/beyond/misc/src/ChangeLoader.java
 */
public class ChangeClassLoader {
    public static void main(String[] args) throws MalformedURLException {
        if (args.length != 1) {
            System.err.println("usage: java ChangeLoader codebase_url");
            System.exit(-1);
        }

        String url = args[0];
        ClassLoader prevCl = Thread.currentThread().getContextClassLoader();

        // Create class loader using given codebase
        // Use prevCl as parent to maintain current visibility
        ClassLoader urlCl = URLClassLoader.newInstance(new URL[]{new URL(url)}, prevCl);

        try {
            // Save class loader so that we can restore later
            Thread.currentThread().setContextClassLoader(urlCl);

            // Expect that environment properties are in
            // application resource file found at "url"
            Context ctx = new InitialContext();

            System.out.println(ctx.lookup("tutorial/report.txt"));

            // Close context when no longer needed
            ctx.close();
        } catch (NamingException e) {
            e.printStackTrace();
        } finally {
            // Restore
            Thread.currentThread().setContextClassLoader(prevCl);
        }
    }
}
