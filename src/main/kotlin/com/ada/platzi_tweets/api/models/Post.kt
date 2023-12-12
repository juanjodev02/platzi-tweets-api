package com.ada.platzi_tweets.api.models

import com.ada.platzi_tweets.api.models.converters.AuthorConverter
import com.ada.platzi_tweets.api.models.converters.LocationConverter
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "posts")
data class Post(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "api_user_id_seq")
        @SequenceGenerator(name = "api_user_id_seq", allocationSize = 1)
        val id: Long = 0,

        @Convert(converter = AuthorConverter::class)
        val author: Author? = null,

        @Column
        var imageUrl: String? = null,

        @Column
        var text: String,

        @Column
        var videoUrl: String? = null,

        @Convert(converter = LocationConverter::class)
        var location: Location? = null,

        @Column
        var hasVideo: Boolean,

        @Column
        var hasImage: Boolean,

        @Column
        var hasLocation: Boolean,

        @CreationTimestamp
        var createdAt: LocalDateTime? = null
)
