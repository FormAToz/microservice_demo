package ru.format.demo.licenseservice.gw.input.rest.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@Slf4j
public class UserContextFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {
        UserContext context = UserContextHolder.getContext();
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        context.setCorrelationId(httpServletRequest.getHeader(UserContext.CORRELATION_ID));
        context.setUserId(httpServletRequest.getHeader(UserContext.USER_ID));
        context.setAuthToken(httpServletRequest.getHeader(UserContext.AUTH_TOKEN));
        context.setOrganizationId(httpServletRequest.getHeader(UserContext.ORGANIZATION_ID));

        log.debug("UserContextFilter: correlation id={}", context.getCorrelationId());

        filterChain.doFilter(httpServletRequest, servletResponse);
    }
}
