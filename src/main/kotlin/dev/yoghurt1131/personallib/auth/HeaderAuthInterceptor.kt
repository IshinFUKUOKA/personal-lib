package dev.yoghurt1131.personallib.auth

import org.springframework.web.servlet.HandlerInterceptor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.method.HandlerMethod
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED

class HeaderAuthInterceptor(private val value: String, private val headerName: String = "x-custom-header") : HandlerInterceptor {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val handerMethod = handler as HandlerMethod
        val annotation = handerMethod.getMethodAnnotation(HumbleAuth::class.java)
        annotation?.let {
            logger.debug("Start Header Base Check...")
            if(request.getHeader(headerName)?.equals(value) != true) {
                response.status = SC_UNAUTHORIZED
                logger.debug("Header Check Failed.")
                return false
            }
            logger.debug("Header Check Succeed.")
            return true
        }
        return true
    }

}