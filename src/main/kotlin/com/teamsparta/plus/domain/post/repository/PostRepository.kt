package com.teamsparta.plus.domain.post.repository

import com.teamsparta.plus.domain.post.model.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository: JpaRepository<Post, Long> {

}