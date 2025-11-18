package common.engine;

import common.utils.DateUtils;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description
 * @author: zpy
 * @Date: 2025/11/18 09:06
 */
public class SessionManager implements Runnable{
    final Logger logger = LoggerFactory.getLogger(getClass());
    final ServletContextImpl servletContext;
    final Map<String,HttpSessionImpl> sessions=new ConcurrentHashMap<>();
    final int inactiveInterval;

    public SessionManager(ServletContextImpl servletContext,int inactiveInterval){
        this.servletContext = servletContext;
        this.inactiveInterval = inactiveInterval;
        Thread t = new Thread(this, "Session-Cleanup-Thread");
        t.setDaemon(true);// 守护线程；当所有非守护线程（用户线程）都结束时，JVM（Java 虚拟机）会退出，而不会等待守护线程执行完毕
        t.start();
    }

    public HttpSession getSession(String sessionId){
        HttpSessionImpl session = sessions.get(sessionId);
        if (session == null){
            session = new HttpSessionImpl(servletContext,sessionId,inactiveInterval);
            sessions.put(sessionId,session);
        }else {
            session.lastAccessedTime = System.currentTimeMillis();
        }
        return session;
    }

    public void remove(HttpSession session){
        this.sessions.remove(session.getId());
    }

    @Override
    public void run() {
        for (;;){
            try {
                Thread.sleep(60_000L);
            } catch (InterruptedException e) {
                break;
            }
            long now = System.currentTimeMillis();
            for (String sessionId : sessions.keySet()) {
                HttpSession session = sessions.get(sessionId);
                if (session.getLastAccessedTime() + session.getMaxInactiveInterval() * 1000L < now) {
                    logger.warn("remove expired session: {}, last access time: {}", sessionId, DateUtils.formatDateTimeGMT(session.getLastAccessedTime()));
                    session.invalidate();
                }
            }
        }

    }
}
