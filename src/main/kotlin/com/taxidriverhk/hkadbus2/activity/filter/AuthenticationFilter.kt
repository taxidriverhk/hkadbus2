package com.taxidriverhk.hkadbus2.activity.filter

import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.ws.rs.HttpMethod

import org.apache.logging.log4j.kotlin.Logging

import com.auth0.jwt.algorithms.Algorithm
import com.google.inject.Singleton
import com.taxidriverhk.hkadbus2.activity.auth.Authenticator

@Singleton
class AuthenticationFilter : Filter {

    companion object : Logging

    lateinit private var authenticator: Authenticator

    override fun init(filterConfig: FilterConfig) {
        val secret = System.getenv("ENCRYPTOR_PASSWORD")
        val authTokenSigner = Algorithm.HMAC256(secret)
        authenticator = Authenticator(authTokenSigner)
    }

    override fun doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse, filterChain: FilterChain) {
        val request = servletRequest as HttpServletRequest
        val response = servletResponse as HttpServletResponse

        logger.info("Authenticating request: ${request.getRequestURI()}")

        if (request.method != HttpMethod.GET && !verifyAuthenticationToken(request)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED)
        } else {
            filterChain.doFilter(servletRequest, servletResponse)
        }
    }

    override fun destroy() {}

    private fun verifyAuthenticationToken(request: HttpServletRequest): Boolean {
        val authToken = request.getHeader("Authorization")
        return if (authToken.isNotBlank()) authenticator.verifyToken(authToken) else false
    }
}
