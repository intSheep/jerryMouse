package common.engine.mapping;

import jakarta.servlet.Servlet;

/**
 * @description ServletMapping
 * @author: zpy
 * @Date: 2025/11/2 08:32
 */
public class ServletMapping extends AbstractMapping{
    public final Servlet servlet;

    public ServletMapping(String url,Servlet servlet) {
        super(url);
        this.servlet = servlet;
    }

    public Servlet getServlet() {
        return servlet;
    }
}
