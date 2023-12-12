package com.ada.platzi_tweets.api.repositories

import com.ada.platzi_tweets.api.models.User
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, Long> {
    fun findByEmail(name: String): User?
}