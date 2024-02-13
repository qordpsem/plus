package com.teamsparta.plus.domain.comment.model

import com.teamsparta.plus.api.comment.dto.CommentResponse
import com.teamsparta.plus.domain.post.model.Post
import com.teamsparta.plus.domain.user.model.User
import jakarta.persistence.*
import org.springframework.data.jpa.domain.AbstractPersistable_.id

@Entity
@Table(name="comment")
class Comment (
        @Column(name="title", nullable = false)
        var title: String,

        @Column(name = "content", nullable = false)
        var content: String,

        @ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name = "postId", nullable = false)
        var post: Post,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="userId", nullable = false)
        val user: User
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?=null
}

fun Comment.toResponse(): CommentResponse {
    return CommentResponse(
            commentId = id!!,
            title = title,
            content = content
    )
}