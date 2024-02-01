package com.teamsparta.plus.api.user.controller

import com.teamsparta.plus.api.user.dto.LoginRequest
import com.teamsparta.plus.api.user.dto.LoginResponse
import com.teamsparta.plus.api.user.dto.SignUpRequest
import com.teamsparta.plus.api.user.dto.UserResponse
import com.teamsparta.plus.domain.user.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class UserController (
        private val userService: UserService
) {
    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<LoginResponse>{
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.login(loginRequest))
    }

    @PostMapping("/signup")
    fun signUp(@RequestBody signUpRequest: SignUpRequest): ResponseEntity<UserResponse>{
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.signUp(signUpRequest))
    }


}