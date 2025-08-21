package ru.format.demo.gw_server.filter;

import org.springframework.http.HttpHeaders;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class FilterUtils {

    public static final String CORRELATION_ID = "tmx-correlation-id";

    @Nullable
    public String getCorrelationId(HttpHeaders requestHeaders) {
        return requestHeaders.getFirst(CORRELATION_ID);
    }

    public ServerWebExchange setCorrelationId(ServerWebExchange exchange, String correlationId) {
        return setRequestHeader(exchange, CORRELATION_ID, correlationId);
    }

    public void setResponseHeader(ServerWebExchange exchange, String name, String value) {
        exchange.getResponse().getHeaders().add(name, value);
    }

    private ServerWebExchange setRequestHeader(ServerWebExchange exchange, String name, String value) {
        return exchange
                .mutate()
                .request(
                        exchange.getRequest().mutate()
                                .header(name, value)
                                .build())
                .build();
    }
}
