package com.taxidriverhk.hkadbus2.activity.filter

import com.google.inject.Singleton
import org.apache.logging.log4j.ThreadContext
import org.apache.logging.log4j.kotlin.Logging

import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import java.util.UUID

@Singleton
class LoggingFilter : Filter {

    companion object : Logging {

        private val REQUEST_ID = "requestId"
    }

    override fun init(filterConfig: FilterConfig) {}

    override fun doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse, filterChain: FilterChain) {
        ThreadContext.put(REQUEST_ID, UUID.randomUUID().toString())
        filterChain.doFilter(servletRequest, servletResponse)
    }

    override fun destroy() {}
}
