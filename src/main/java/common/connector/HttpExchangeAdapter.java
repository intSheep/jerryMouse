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
        return "";
    }

    @Override
    public URI gerRequestURI() {
        return null;
    }

    @Override
    public Headers getResponseHeaders() {
        return null;
    }

    @Override
    public void sendResponseHeaders(int rCode, long responseLength) throws IOException {

    }

    @Override
    public OutputStream getResponseBody() {
        return null;
    }
}
