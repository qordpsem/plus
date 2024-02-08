package com.teamsparta.plus.domain.post.service

import com.teamsparta.plus.api.post.dto.CreatePostRequest
import com.teamsparta.plus.api.post.dto.PostResponse
import com.teamsparta.plus.api.post.dto.UpdatePostRequest
import com.teamsparta.plus.domain.exception.ModelNotFoundException
import com.teamsparta.plus.domain.post.model.Post
import com.teamsparta.plus.domain.post.model.toResponse
import com.teamsparta.plus.domain.post.repository.PostRepository
import com.teamsparta.plus.domain.user.model.User
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PostServiceImpl(
        private val postRepository: PostRepository
) : PostService{

    override fun getPostList(pageable: Pageable): Page<PostResponse> {
        return postRepository.findAll(pageable).map{it.toResponse()}
    }

    override fun getPost(postId: Long): PostResponse {
        val post = postRepository.findByIdOrNull(postId)?: throw ModelNotFoundException("Post",postId)
        return post.toResponse()
    }

    @Transactional
    override fun createPost(request: CreatePostRequest): PostResponse {TODO()}
//        return postRepository.save(
//                Post(
//                     title = request.title,
//                        content = request.content,
//
//                )
//        )


    @Transactional
    override fun updatePost(postId: Long, request: UpdatePostRequest): PostResponse {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun deletePost(postId: Long) {
        TODO("Not yet implemented")
    }
}