package com.mutoe.realworld.adapter.filter

import com.mutoe.realworld.adapter.security.utils.JwtTokenUtil
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthenticationFilter(
    private val jwtTokenUtil: JwtTokenUtil,
) : OncePerRequestFilter() {
    private val tokenHeader = "authorization"
    private val tokenPrefix = "Bearer "

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val token = getTokenFromHttpRequest(request)
        if (token != null) {
            if (!jwtTokenUtil.validateToken(token)) {
                return response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized: Auth token is illegal")
            }
            val authentication = jwtTokenUtil.getAuthentication(token)
            SecurityContextHolder.getContext().authentication = authentication
        }

        filterChain.doFilter(request, response)
    }

    private fun getTokenFromHttpRequest(request: HttpServletRequest): String? {
        val authHeader = request.getHeader(tokenHeader)
        if (authHeader == null || !authHeader.startsWith(tokenPrefix)) return null
        return authHeader.substring(tokenPrefix.length)
    }
}
