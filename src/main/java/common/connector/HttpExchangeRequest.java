package common.connector;

import java.net.URI;

public interface HttpExchangeRequest {
    String getRequestMethod();
    URI gerRequestURI();
}
