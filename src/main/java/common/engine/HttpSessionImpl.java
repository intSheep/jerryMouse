package common.engine;

import common.engine.support.Attributes;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;

import java.util.Enumeration;

/**
 * @description
 * @author: zpy
 * @Date: 2025/11/18 08:49
 */
public class HttpSessionImpl implements HttpSession {
    final ServletContextImpl servletContext;

    String   sessionId;
    int maxInactiveInterval;
    long creationTime;
    long lastAccessedTime;
    Attributes attributes ;

    public HttpSessionImpl (ServletContextImpl servletContext,String sessionId,int interval){
        this.servletContext = servletContext;
        this.sessionId = sessionId;
        this.creationTime = this.lastAccessedTime  = System.currentTimeMillis();
        this.attributes = new Attributes();
        setMaxInactiveInterval(interval);
    }
    @Override
    public long getCreationTime() {
        return this.creationTime;
    }

    @Override
    public String getId() {
        return this.sessionId;
    }

    @Override
    public long getLastAccessedTime() {
        return this.lastAccessedTime;
    }

    @Override
    public ServletContext getServletContext() {
        return this.servletContext;
    }

    @Override
    public void setMaxInactiveInterval(int i) {
        this.maxInactiveInterval = i;
    }

    @Override
    public int getMaxInactiveInterval() {
        return this.maxInactiveInterval;
    }

    @Override
    public Object getAttribute(String s) {
        checkValid();
        return this.attributes.getAttribute(s);
    }

    @Override
    public Enumeration<String> getAttributeNames() {
       checkValid();
       return this.attributes.getAttributeNames();
    }

    @Override
    public void setAttribute(String name, Object value) {
        checkValid();
        if (value == null){
            removeAttribute(name);
        }else {
            this.attributes.setAttribute(name,value);
        }
    }

    @Override
    public void removeAttribute(String s) {
        checkValid();
        this.attributes.removeAttribute(s);

    }

    @Override
    public void invalidate() {
        checkValid();
        this.servletContext.sessionManager.remove(this);
        this.sessionId = null;
    }

    void checkValid(){
        if (this.sessionId==null){
            throw new IllegalArgumentException("Session already invalidated");
        }
    }

    @Override
    public boolean isNew() {
        return false;
    }
}
