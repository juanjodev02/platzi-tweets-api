package com.ada.platzi_tweets.api.models

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User (
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "api_user_id_seq")
        @SequenceGenerator(name = "api_user_id_seq", allocationSize = 1)
        var id: Long = 0,

        @Column
        var names: String = "",

        @Column(unique = true)
        var email: String = "",

        @Column
        var password: String = ""
)
