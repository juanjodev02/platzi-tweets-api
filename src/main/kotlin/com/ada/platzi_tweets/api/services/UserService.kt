package com.ada.platzi_tweets.api.services

import com.ada.platzi_tweets.api.models.User
import com.ada.platzi_tweets.api.repositories.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService (
        private val userRepository: UserRepository
) {
    fun findById(id: Long): User? {
        return userRepository.findByIdOrNull(id)
    }

    fun save(user: User): User {
        return  userRepository.save(user)
    }

    fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }
}