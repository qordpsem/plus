package com.teamsparta.plus.domain.exception

data class InvalidCredentialsException (
    override val message: String? = "자격이 유효하지 않습니다"
    ) : RuntimeException()