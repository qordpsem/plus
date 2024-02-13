package com.teamsparta.plus.domain.post.service

import com.teamsparta.plus.api.post.dto.CreatePostRequest
import com.teamsparta.plus.api.post.dto.PostResponse
import com.teamsparta.plus.api.post.dto.UpdatePostRequest
import com.teamsparta.plus.domain.exception.InvalidCredentialsException
import com.teamsparta.plus.domain.exception.ModelNotFoundException
import com.teamsparta.plus.domain.post.model.Post
import com.teamsparta.plus.domain.post.model.toResponse
import com.teamsparta.plus.domain.post.repository.PostRepository
import com.teamsparta.plus.domain.user.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PostServiceImpl(
        private val postRepository: PostRepository,
        private val userRepository: UserRepository
) : PostService{

    override fun getPostList(pageable: Pageable): Page<PostResponse> {
        return postRepository.findAll(pageable).map{it.toResponse()}
    }

    override fun getPost(postId: Long): PostResponse {
        val post = postRepository.findByIdOrNull(postId)?: throw ModelNotFoundException("Post",postId)
        return post.toResponse()
    }

    @Transactional
    override fun createPost(request: CreatePostRequest, token: String): PostResponse {
        val user = userRepository.findByToken(token)
        if (user == null || user.token != token) {
            throw InvalidCredentialsException("유효하지 않은 토큰입니다")
        }
            return postRepository.save(
                    Post(
                            title=request.title,
                            content = request.content,
                            user=user
                    )
            ).toResponse()
    }


    @Transactional
    override fun updatePost(postId: Long, request: UpdatePostRequest, token: String): PostResponse {
        val user = userRepository.findByToken(token)
        if (user == null || user.token != token){throw InvalidCredentialsException("유효하지 않은 토큰입니다")
        }
        return postRepository.save(
                Post(
                        title=request.title,
                        content= request.content,
                        user= user
                )
        ).toResponse()
    }

    @Transactional
    override fun deletePost(postId: Long, token: String) {
        val user = userRepository.findByToken(token)
        if (user == null || user.token != token){throw InvalidCredentialsException("유효하지 않는 토큰입니다")
        }
        val post = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("Post", postId)
        postRepository.delete(post)
    }
}