package me.hengwei.t.javaee.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

/**
 * Mina client demo.
 */
public class TimeClientHandler extends IoHandlerAdapter {
    public TimeClientHandler() {}

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) {
        session.close(true);
    }

    @Override
    public void sessionOpened(IoSession session) {
        String msg = "Hi, I'm Will";
        session.write(msg);
    }

    @Override
    public void messageReceived(IoSession session, Object message) {
        System.out.println(message);
        session.close(false);
    }
}
