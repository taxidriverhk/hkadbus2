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
import java.io.IOException;
import java.util.Objects;
import java.util.Set;

@Singleton
@Log4j2
public class CORSFilter implements Filter {

    private static final String LOCAL_HOST_PATTERN = "http://localhost";
    private static final Set<String> ALLOWED_ORIGINS = ImmutableSet.of("https://hkadbus2.taxidriverhk.com");

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain)
            throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        final String origin = request.getHeader("Origin");
        log.info("Checking if origin {} is allowed to make API calls", origin);
        if (Objects.nonNull(origin) && (ALLOWED_ORIGINS.contains(origin) || origin.startsWith(LOCAL_HOST_PATTERN))) {
            response.addHeader("Access-Control-Allow-Origin", origin);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {}
}
