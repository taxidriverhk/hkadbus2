package com.taxidriverhk.hkadbus2.activity.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.HttpMethod;

import org.apache.commons.lang3.StringUtils;

import com.auth0.jwt.algorithms.Algorithm;
import com.google.inject.Singleton;
import com.taxidriverhk.hkadbus2.activity.auth.Authenticator;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Singleton
public class AuthenticationFilter implements Filter {

    private Authenticator authenticator;

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        final String secret = System.getenv("ENCRYPTOR_PASSWORD");
        final Algorithm authTokenSigner = Algorithm.HMAC256(secret);
        this.authenticator = new Authenticator(authTokenSigner);
    }

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain)
            throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        log.info("Authenticating request: {}", request.getRequestURI());

        if (!request.getMethod().equals(HttpMethod.GET) && !verifyAuthenticationToken(request)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {}

    private boolean verifyAuthenticationToken(final HttpServletRequest request) {
        final String authToken = request.getHeader("Authorization");
        if (StringUtils.isBlank(authToken)) {
            return false;
        }

        return authenticator.verifyToken(authToken);
    }
}
