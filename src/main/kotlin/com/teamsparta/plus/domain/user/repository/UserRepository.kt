package com.teamsparta.plus.domain.user.repository

import com.teamsparta.plus.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun existsByNickname (nickname: String): Boolean

    fun findByNickname(nickname:String) : User?
}