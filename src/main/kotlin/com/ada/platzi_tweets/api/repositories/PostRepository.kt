package com.ada.platzi_tweets.api.repositories

import com.ada.platzi_tweets.api.models.Post
import org.springframework.data.repository.CrudRepository

interface PostRepository: CrudRepository<Post, Long> {
}