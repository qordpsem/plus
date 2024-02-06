package com.teamsparta.plus.domain.post.model

import com.teamsparta.plus.api.post.dto.PostResponse
import com.teamsparta.plus.domain.user.model.User
import jakarta.persistence.*
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import java.time.LocalDateTime

@Entity
@Table(name="post")
class Post (
        @Column(name="title", nullable=false)
        var title: String,

        @Column(name="content", nullable=false)
        var content: String,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "userId", nullable=false)
        var user: User



){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?=null
}

fun Post.toResponse(): PostResponse {
    return PostResponse(
            postId = id!!,
            nickName = user.nickname,
            title = title,
            content = content,
            createdAt = LocalDateTime.now()
    )
}