package com.teamsparta.plus.api.post.dto

import java.time.LocalDateTime

data class PostResponse (
        val postId: Long,
        val nickName: String,
        val title: String,
        val content: String,
        val createdAt: LocalDateTime
    )