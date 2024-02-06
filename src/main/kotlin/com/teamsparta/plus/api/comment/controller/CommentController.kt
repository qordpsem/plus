package com.teamsparta.plus.api.comment.controller

import com.teamsparta.plus.api.comment.dto.CommentResponse
import com.teamsparta.plus.api.comment.dto.CreateCommentRequest
import com.teamsparta.plus.api.comment.dto.UpdateCommentRequest
import com.teamsparta.plus.domain.comment.service.CommentService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/v1/posts/{postId}/comments")
@RestController
class CommentController(
        private val commentService: CommentService
){
    @PostMapping
    fun createComment(
            @RequestBody createCommentRequest: CreateCommentRequest
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commentService.createComment(createCommentRequest))
    }

    @GetMapping
    fun getCommentList(
            @PageableDefault(size=5, sort=["creationDate,desc"]) pageable: Pageable
    ): ResponseEntity<Page<CommentResponse>>{
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getCommentList(pageable))
    }

    @PutMapping("/{commentId}")
    fun updateComment(
            @PathVariable commentId: Long,
            @RequestBody updateCommentRequest: UpdateCommentRequest
    )
    : ResponseEntity<CommentResponse>{
        return ResponseEntity.status(HttpStatus.OK)
                .body(commentService.updateComment(commentId,updateCommentRequest))
    }

    @DeleteMapping("/{commentId}")
    fun deleteComment(
            @PathVariable commentId: Long) : ResponseEntity<Unit>{
        commentService.deleteComment(commentId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    }