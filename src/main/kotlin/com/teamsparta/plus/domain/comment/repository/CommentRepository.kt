package com.teamsparta.plus.domain.comment.repository

import com.teamsparta.plus.domain.comment.model.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository: JpaRepository<Comment,Long> {
}