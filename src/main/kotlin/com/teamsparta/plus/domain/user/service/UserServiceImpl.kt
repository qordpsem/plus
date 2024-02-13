package com.teamsparta.plus.domain.user.service

import com.teamsparta.plus.api.user.dto.LoginRequest
import com.teamsparta.plus.api.user.dto.LoginResponse
import com.teamsparta.plus.api.user.dto.SignUpRequest
import com.teamsparta.plus.api.user.dto.UserResponse
import com.teamsparta.plus.domain.exception.InvalidCredentialsException
import com.teamsparta.plus.domain.exception.ModelNotFoundException
import com.teamsparta.plus.domain.user.model.User
import com.teamsparta.plus.domain.user.model.toResponse
import com.teamsparta.plus.domain.user.repository.UserRepository
import com.teamsparta.plus.infra.security.jwt.JwtPlugin
import jakarta.transaction.Transactional
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl (
        private val userRepository: UserRepository,
        private val passwordEncoder: PasswordEncoder,
        private val jwtPlugin: JwtPlugin
) : UserService{

    @Transactional
    override fun signUp(request: SignUpRequest): UserResponse {
        if (userRepository.existsByNickname(request.nickname)) {
            throw InvalidCredentialsException("중복된 닉네임입니다.")
        }

        return userRepository.save(
                User(
                        nickname = request.nickname,
                        password = passwordEncoder.encode(request.password)
                )
        ).toResponse()

    }


    @Transactional
    override fun login(request: LoginRequest): LoginResponse {
        val user = userRepository.findByNickname(request.nickname)
        if (user == null || !passwordEncoder.matches(request.password, user.password)){
            throw InvalidCredentialsException("닉네임 또는 패스워드를 확인해주세요.")
        }
        return LoginResponse(
                accessToken= jwtPlugin.generateAccessToken(
                        nickname = user.nickname),
                id = user.id,
                nickname = user.nickname
        )
    }

}