package com.teamsparta.plus.domain.comment.service

import com.teamsparta.plus.api.comment.dto.CommentResponse
import com.teamsparta.plus.api.comment.dto.CreateCommentRequest
import com.teamsparta.plus.api.comment.dto.UpdateCommentRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CommentService {

    fun createComment(request:CreateCommentRequest): CommentResponse
    fun getCommentList(pageable: Pageable): Page<CommentResponse>
    fun updateComment(commentId:Long, request: UpdateCommentRequest): CommentResponse
    fun deleteComment(commentId:Long)

}