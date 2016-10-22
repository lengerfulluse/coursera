package me.hengwei.t.javaee.mina;

import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * Created by hengwei on 22/10/2016.
 */
public class MinaTimeClient {
    private static final String HOSTNAME = "localhost";
    private static final int PORT = 8080;
    private static final long CONNECT_TIMEOUT = 30*1000L; // 30 seconds
    /* Set this to false to use object serialization instead of custom codec. */
    private static final boolean USE_CUSTOM_CODEC = true;

    public static void main(String[] args) throws Exception {
        NioSocketConnector connector = new NioSocketConnector();

        /* configure the service endpoint */
        connector.setConnectTimeoutMillis(CONNECT_TIMEOUT);
        connector.getFilterChain().addLast("logger", new LoggingFilter());
        connector.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
        connector.setHandler(new TimeClientHandler());
        IoSession session;
        for (;;) {
            try {
                ConnectFuture future = connector.connect(new InetSocketAddress(HOSTNAME, PORT));
                future.awaitUninterruptibly();
                session = future.getSession();
                break;
            } catch (RuntimeIoException e) {
                System.err.println("Failed to connect.");
                e.printStackTrace();
                Thread.sleep(5000);
            }
        }
        /* wait until the summation is done */
        session.getCloseFuture().awaitUninterruptibly();
        connector.dispose();

    }
}
