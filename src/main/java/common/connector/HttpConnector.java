package common.connector;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import common.engine.HttpServletRequestImpl;
import common.engine.HttpServletResponseImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @description HttpConnector
 * @author: zpy
 * @Date: 2025/10/24
 */
public class HttpConnector implements HttpHandler,AutoCloseable {
    final HttpServer httpServer;

    final Logger logger = LoggerFactory.getLogger(getClass());

    public HttpConnector() throws  IOException{
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
        var request = new HttpServletRequestImpl(adapter);
        try {
            process(request,response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }


    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String name = request.getParameter("name");
       String html = "<h1>Hello,"+(name != null ? name : "World")+"!</h1>";
       response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        pw.write(html);
        pw.close();
    }

    @Override
    public void close() throws Exception {
        this.httpServer.stop(3);
    }
}
