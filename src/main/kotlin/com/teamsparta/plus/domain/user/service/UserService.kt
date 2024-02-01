package com.teamsparta.plus.domain.user.service

import com.teamsparta.plus.api.user.dto.LoginRequest
import com.teamsparta.plus.api.user.dto.LoginResponse
import com.teamsparta.plus.api.user.dto.SignUpRequest
import com.teamsparta.plus.api.user.dto.UserResponse

interface UserService {
    fun signUp(request: SignUpRequest) : UserResponse
    fun login(request: LoginRequest): LoginResponse
}