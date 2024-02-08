package com.teamsparta.plus.api.user.dto

data class LoginResponse(
        val accessToken: String,
        var id: Long?,
        var nickname: String
)