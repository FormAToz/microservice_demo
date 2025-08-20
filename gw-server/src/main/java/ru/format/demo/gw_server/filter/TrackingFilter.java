package ru.format.demo.gw_server.filter;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@AllArgsConstructor
@Component
@Slf4j
public class TrackingFilter implements GlobalFilter {

    private final FilterUtils filterUtils;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
        exchange = filterUtils.setCorrelationId(exchange, getCorrelationId(requestHeaders));
        return chain.filter(exchange);
    }

    private String getCorrelationId(HttpHeaders requestHeaders) {
        boolean correlationIdPresent = filterUtils.getCorrelationId(requestHeaders) != null;
        String correlationId;
        if (correlationIdPresent) {
            log.debug("tmx-correlation-id found in tracking filter: {}", filterUtils.getCorrelationId(requestHeaders));
            correlationId = filterUtils.getCorrelationId(requestHeaders);
        } else {
            correlationId = generateCorrelationId();
            log.debug("tmx-correlation-id generated in tracking filter: {}", correlationId);
        }
        return correlationId;
    }

    private String generateCorrelationId() {
        return UUID.randomUUID().toString();
    }
}
