package com.teamsparta.plus.infra.security.jwt

import com.teamsparta.plus.infra.security.UserPrincipal
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.web.authentication.WebAuthenticationDetails

class JwtAuthenticationToken(
        private val principal : UserPrincipal,
        details: WebAuthenticationDetails, authorities: MutableCollection<out GrantedAuthority>?
):AbstractAuthenticationToken(authorities){
    init {
        super.setAuthenticated(true)
        super.setDetails(details)
    }

    override fun getCredentials() = null

    override fun getPrincipal() = principal

    override fun isAuthenticated(): Boolean {
        return true
    }
}