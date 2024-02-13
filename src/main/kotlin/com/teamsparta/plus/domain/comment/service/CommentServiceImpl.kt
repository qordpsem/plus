package com.teamsparta.plus.domain.comment.service

import com.teamsparta.plus.api.comment.dto.CommentResponse
import com.teamsparta.plus.api.comment.dto.CreateCommentRequest
import com.teamsparta.plus.api.comment.dto.UpdateCommentRequest
import com.teamsparta.plus.domain.comment.model.Comment
import com.teamsparta.plus.domain.comment.model.toResponse
import com.teamsparta.plus.domain.comment.repository.CommentRepository
import com.teamsparta.plus.domain.exception.InvalidCredentialsException
import com.teamsparta.plus.domain.exception.ModelNotFoundException
import com.teamsparta.plus.domain.post.repository.PostRepository
import com.teamsparta.plus.domain.user.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl (
        private val commentRepository: CommentRepository,
        private val postRepository: PostRepository,
        private val userRepository: UserRepository
): CommentService {

    @Transactional
    override fun createComment(request: CreateCommentRequest, token: String): CommentResponse {
        val post = postRepository.findByIdOrNull(request.postId)?: throw ModelNotFoundException("Post",request.postId)
        val user = userRepository.findByToken(token)
        if (user == null || user.token != token) {
            throw InvalidCredentialsException("유효하지 않은 토큰입니다")}
            return commentRepository.save(
                    Comment(
                            title = request.title,
                            content = request.content,
                            post = post!!,
                            user= user)
            ).toResponse()


    }

    override fun getCommentList(pageable: Pageable): Page<CommentResponse> {
        return commentRepository.findAll(pageable).map{it.toResponse()}
    }

    @Transactional
    override fun updateComment(commentId: Long, request: UpdateCommentRequest): CommentResponse {
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment", commentId)
        comment.title = request.title
        comment.content = request.content
        return commentRepository.save(comment).toResponse()
    }

    @Transactional
    override fun deleteComment(commentId: Long) {
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment", commentId)
        commentRepository.delete(comment)
    }

}