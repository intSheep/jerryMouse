package common.connector;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import common.engine.HttpServletRequestImpl;
import common.engine.HttpServletResponseImpl;
import common.engine.ServletContextImpl;
import common.engine.filter.HelloFilter;
import common.engine.filter.LogFilter;
import common.engine.servlet.HelloServlet;
import common.engine.servlet.IndexServlet;
import common.engine.servlet.LoginServlet;
import common.engine.servlet.LogoutServlet;
import jakarta.servlet.ServletException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * @description HttpConnector
 * @author: zpy
 * @Date: 2025/10/24
 */
public class HttpConnector implements HttpHandler,AutoCloseable {
    final HttpServer httpServer;
    final ServletContextImpl servletContext ;

    final Logger logger = LoggerFactory.getLogger(getClass());

    public HttpConnector() throws  IOException{
        this.servletContext = new ServletContextImpl();
        this.servletContext.initServlets(List.of(IndexServlet.class, HelloServlet.class, LoginServlet.class, LogoutServlet.class));
        this.servletContext.initFilters(List.of(HelloFilter.class, LogFilter.class));
        String host = "0.0.0.0";
        int port = 8080;
        this.httpServer = HttpServer.create(
                new java.net.InetSocketAddress(host,port),
                0,
                "/",
                this);
        this.httpServer.start();
        logger.info("HTTP Server started at " + host + ":" + port);
    }
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        var adapter = new HttpExchangeAdapter(exchange);
        var response = new HttpServletResponseImpl(adapter);
        var request = new HttpServletRequestImpl(this.servletContext, adapter,response);
        try {
           this.servletContext.process(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        this.httpServer.stop(3);
    }
}
