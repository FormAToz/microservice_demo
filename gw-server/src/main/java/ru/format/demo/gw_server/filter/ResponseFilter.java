package ru.format.demo.gw_server.filter;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Configuration
@Slf4j
public class ResponseFilter {

    private final FilterUtils filterUtils;

    @Bean
    public GlobalFilter postGlobalFilter() {
        return ((exchange, chain) -> chain.filter(exchange)
                .then(Mono.fromRunnable(
                        () -> {
                            HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
                            setAuthHeader(requestHeaders, exchange);
                            setCorrelationIdHeader(requestHeaders, exchange);
                        })
                )
        );
    }

    private void setAuthHeader(HttpHeaders requestHeaders, ServerWebExchange exchange) {
        filterUtils.setResponseHeader(exchange, HttpHeaders.AUTHORIZATION, filterUtils.getAuthToken(requestHeaders));
    }

    private void setCorrelationIdHeader(HttpHeaders requestHeaders, ServerWebExchange exchange) {
        String correlationId = filterUtils.getCorrelationId(requestHeaders);
        log.debug("Adding the correlation ID to the outbound headers: {}", correlationId);
        filterUtils.setResponseHeader(exchange, FilterUtils.CORRELATION_ID, correlationId);
        log.debug("Completing outgoing request for {}", exchange.getRequest().getURI());
    }
}
