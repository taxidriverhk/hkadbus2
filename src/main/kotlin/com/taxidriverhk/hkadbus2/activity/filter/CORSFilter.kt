package com.taxidriverhk.hkadbus2.activity.filter

import org.apache.logging.log4j.kotlin.Logging
import com.google.inject.Singleton

import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Singleton
class CORSFilter : Filter {

    companion object : Logging {

        val LOCAL_HOST_PATTERN = "http://localhost"
        val ALLOWED_ORIGINS = hashSetOf("https://hkadbus2.taxidriverhk.com")
    }

    override fun init(filterConfig: FilterConfig) {}

    override fun doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse, filterChain: FilterChain) {
        val request = servletRequest as HttpServletRequest
        val response = servletResponse as HttpServletResponse

        val origin = request.getHeader("Origin")
        logger.info("Checking if origin ${origin} is allowed to make API calls")
        if (ALLOWED_ORIGINS.contains(origin) || origin.startsWith(LOCAL_HOST_PATTERN)) {
            response.addHeader("Access-Control-Allow-Origin", origin)
        }

        filterChain.doFilter(servletRequest, servletResponse)
    }

    override fun destroy() {}
}
