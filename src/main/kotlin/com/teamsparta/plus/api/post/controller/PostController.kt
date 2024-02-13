package com.teamsparta.plus.api.post.controller

import com.teamsparta.plus.api.post.dto.CreatePostRequest
import com.teamsparta.plus.api.post.dto.PostResponse
import com.teamsparta.plus.api.post.dto.UpdatePostRequest
import com.teamsparta.plus.domain.post.service.PostService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/posts")
class PostController (
        private val postService: PostService
){
    @GetMapping
    fun getPostList(
            @PageableDefault(size=5, sort=["creationDate,desc"]) pageable: Pageable
    ): ResponseEntity<Page<PostResponse>>{
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPostList(pageable))
    }

    @GetMapping("/{postId}")
    fun getPost(
            @PathVariable postId: Long): ResponseEntity<PostResponse>{
     return ResponseEntity
             .status(HttpStatus.OK)
             .body(postService.getPost(postId))
    }

    @PostMapping
    fun createPost(
            @RequestBody createPostRequest: CreatePostRequest): ResponseEntity<PostResponse>{
        return ResponseEntity
            .status(HttpStatus.CREATED)
                .body(postService.createPost(createPostRequest))
    }

    @PutMapping("/{postId}")
    fun updatePost(
            @PathVariable postId: Long,
            @RequestBody updatePostRequest: UpdatePostRequest
    ): ResponseEntity<PostResponse> {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(postService.updatePost(postId, updatePostRequest))
    }

    @DeleteMapping("/{postId}")
    fun deletePost(
            @PathVariable postId: Long) : ResponseEntity<Unit>{
        postService.deletePost(postId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

}