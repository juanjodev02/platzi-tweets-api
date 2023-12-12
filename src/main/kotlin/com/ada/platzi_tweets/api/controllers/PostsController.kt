package com.ada.platzi_tweets.api.controllers

import com.ada.platzi_tweets.api.config.toUser
import com.ada.platzi_tweets.api.dtos.DeletePostDto
import com.ada.platzi_tweets.api.dtos.PublishPostDto
import com.ada.platzi_tweets.api.models.Author
import com.ada.platzi_tweets.api.models.Post
import com.ada.platzi_tweets.api.repositories.PostRepository
import org.springframework.data.repository.query.Param
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class PostsController(
        private val postRepository: PostRepository
) {
    @GetMapping("/posts")
    fun getPosts(): MutableIterable<Post> {
        return postRepository.findAll()
    }

    @PostMapping("/posts")
    fun publishPost(@RequestBody data: PublishPostDto, authentication: Authentication): ArrayList<Post> {
        val user = authentication.toUser()
        val post = Post(
                text = data.text,
                imageUrl = data.imageUrl,
                videoUrl = data.videoUrl,
                location = data.location,
                hasImage = data.imageUrl != null,
                hasVideo = data.videoUrl != null,
                hasLocation = data.location != null,
                author = Author(
                        email = user.email,
                ),
        )
        postRepository.save(post)
        val posts =  ArrayList<Post>()
        posts.add(post)
        return posts
    }

    @DeleteMapping("/posts/{id}")
    fun deletePost(@PathVariable id: Long): DeletePostDto {
        try {
            postRepository.deleteById(id)
        } catch (e: Exception) {
            return DeletePostDto(
                    isDone = false,
                    message = "Error deleting post"
            )
        }
        return DeletePostDto(
                isDone = true,
                message = "Ok"
        )
    }
}