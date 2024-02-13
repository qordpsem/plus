package com.teamsparta.plus.api.user.dto

import com.teamsparta.plus.domain.user.validation.ValidNickname
import org.springframework.validation.annotation.Validated


@Validated
data class SignUpRequest(
        @field : ValidNickname
        var nickname: String,
        var password: String,
        var passwordCheck: String
)