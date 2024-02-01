package com.teamsparta.plus.domain.user.model

import com.teamsparta.plus.api.user.dto.UserResponse
import jakarta.persistence.*

@Entity
@Table(name="app_user")
class User(
        @Column(name="nickname", nullable=false)
        var nickname: String,

        @Column(name="password", nullable=false)
        var password: String
)  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun User.toResponse(): UserResponse{
    return UserResponse(
            id = id!!,
            nickname = nickname
    )
}