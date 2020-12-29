package com.taxidriverhk.hkadbus2.activity.filter;

import com.google.common.collect.ImmutableSet;
import com.google.inject.Singleton;
import lombok.extern.log4j.Log4j2;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.HttpMethod;
import java.io.IOException;
import java.util.Set;

@Log4j2
@Singleton
public class AuthenticationFilter implements Filter {

    private static final Set<String> AUTHENTICATED_METHODS = ImmutableSet.of(
            "category/*"
    );

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain)
            throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        log.info("Authenticating request: {}", request.getRequestURI());

        if (!request.getMethod().equals(HttpMethod.GET)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {}
}
