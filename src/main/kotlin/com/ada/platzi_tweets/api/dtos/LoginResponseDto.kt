package com.ada.platzi_tweets.api.dtos

import com.ada.platzi_tweets.api.models.User

data class LoginResponseDto(
        val user: User,
        val token: String
)
