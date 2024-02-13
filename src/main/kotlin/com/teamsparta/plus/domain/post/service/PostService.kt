package com.teamsparta.plus.domain.post.service

import com.teamsparta.plus.api.post.dto.CreatePostRequest
import com.teamsparta.plus.api.post.dto.PostResponse
import com.teamsparta.plus.api.post.dto.UpdatePostRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PostService {
    fun getPostList(pageable: Pageable): Page<PostResponse>
    fun getPost(postId:Long): PostResponse
    fun createPost(request: CreatePostRequest) : PostResponse
    fun updatePost(postId:Long, request: UpdatePostRequest) : PostResponse
    fun deletePost(postId:Long)


}