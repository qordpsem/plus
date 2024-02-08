package com.teamsparta.plus.infra.security

import org.springframework.security.core.GrantedAuthority

data class UserPrincipal (
        val id: Long,
        val nickname: String
){
    constructor(id:Long, nickname: String): this(
            id, nickname
    )
}