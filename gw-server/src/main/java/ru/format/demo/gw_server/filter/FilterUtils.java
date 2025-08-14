package ru.format.demo.gw_server.filter;

import org.springframework.http.HttpHeaders;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Optional;


@Component
public class FilterUtils {

    public static final String CORRELATION_ID = "tmx-correlation-id";

    @Nullable
    public String getCorrelationId(HttpHeaders requestHeaders) {
        return Optional.ofNullable(requestHeaders.get(CORRELATION_ID))
                .flatMap(headers -> headers.stream().findFirst())
                .orElse(null);
    }

    public ServerWebExchange setRequestHeader(ServerWebExchange exchange, String name, String value) {
        return exchange
                .mutate()
                .request(
                        exchange.getRequest().mutate()
                                .header(name, value)
                                .build())
                .build();
    }

    public ServerWebExchange setCorrelationId(ServerWebExchange exchange, String correlationId) {
        return this.setRequestHeader(exchange, CORRELATION_ID, correlationId);
    }
}
