package com.teamsparta.plus.domain.comment.service

import com.teamsparta.plus.api.comment.dto.CommentResponse
import com.teamsparta.plus.api.comment.dto.CreateCommentRequest
import com.teamsparta.plus.api.comment.dto.UpdateCommentRequest
import com.teamsparta.plus.domain.comment.repository.CommentRepository
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl (
        private val commentRepository: CommentRepository
): CommentService {

    @Transactional
    override fun createComment(request: CreateCommentRequest): CommentResponse {
        TODO("Not yet implemented")
    }

    override fun getCommentList(pageable: Pageable): Page<CommentResponse> {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun updateComment(commentId: Long, request: UpdateCommentRequest): CommentResponse {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun deleteComment(commentId: Long) {
        TODO("Not yet implemented")
    }

}