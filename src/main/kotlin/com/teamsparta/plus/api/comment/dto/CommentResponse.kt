package com.teamsparta.plus.api.comment.dto


data class CommentResponse (
        val commentId: Long,
        val title: String,
        val content: String
)