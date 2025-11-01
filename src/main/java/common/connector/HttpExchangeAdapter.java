package common.connector;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

/**
 * @description HttpExchange to servlet
 * @author: zpy
 * @Date: 2025/10/24
 */
public class HttpExchangeAdapter implements HttpExchangeRequest, HttpExchangeResponse {
    final HttpExchange exchange;

    public HttpExchangeAdapter(HttpExchange exchange) {
        this.exchange = exchange;
    }

    @Override
    public String getRequestMethod() {
        return exchange.getRequestMethod();
    }

    @Override
    public URI gerRequestURI() {
        return exchange.getRequestURI();
    }

    @Override
    public Headers getResponseHeaders() {
        return exchange.getResponseHeaders();
    }

    @Override
    public void sendResponseHeaders(int rCode, long responseLength) throws IOException {
        exchange.sendResponseHeaders(rCode, responseLength);
    }

    @Override
    public OutputStream getResponseBody() {
        return exchange.getResponseBody();
    }
}
