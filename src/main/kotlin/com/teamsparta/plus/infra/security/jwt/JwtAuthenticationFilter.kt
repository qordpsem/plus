package com.teamsparta.plus.infra.security.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
        private val jwtPlugin : JwtPlugin
) : OncePerRequestFilter() {

    companion object{
        private val BEARER_PATTER = Regex("^Bearer(.+?)$")
    }

    override fun doFilterInternal(
            request: HttpServletRequest,
            response: HttpServletResponse,
            filterChain: FilterChain
    ) {
        val jwt = request.getBearerToken()

        if (jwt != null){
            jwtPlugin.validateToken(jwt)
                    .onSuccess {
                        val userId = it.payload.subject.toLong()
                        val nickname = it.payload.get("nickname", String::class.java)
                    }
        }
    }

    private fun HttpServletRequest.getBearerToken(): String?{
        val headerValue = this.getHeader(HttpHeaders.AUTHORIZATION) ?:  return null
        return BEARER_PATTER.find(headerValue)?.groupValues?.get(1)
    }
}