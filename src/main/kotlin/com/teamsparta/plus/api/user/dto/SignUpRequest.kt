package com.teamsparta.plus.api.user.dto

data class SignUpRequest (
 var nickname: String,
 var password: String,
 var passwordCheck: String
)