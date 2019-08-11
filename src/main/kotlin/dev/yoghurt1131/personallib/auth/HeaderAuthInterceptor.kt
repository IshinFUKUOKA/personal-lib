package dev.yoghurt1131.personallib.auth

import org.springframework.web.servlet.HandlerInterceptor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED

class HeaderAuthInterceptor(private val key: String) : HandlerInterceptor {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)
    private val headerName: String = "x-custom-header"

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        logger.info("Start Header Base Authentication.")
        if(request.getHeader(headerName)?.equals(key) != true) {
            response.status = SC_UNAUTHORIZED
        }
        return true
    }

}