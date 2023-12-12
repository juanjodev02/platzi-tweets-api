package com.ada.platzi_tweets.api.dtos

import com.ada.platzi_tweets.api.models.Location

data class PublishPostDto(
        val text: String,
        val imageUrl: String?,
        val videoUrl: String?,
        val location: Location?
)
