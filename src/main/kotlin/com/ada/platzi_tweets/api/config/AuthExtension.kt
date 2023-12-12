package com.ada.platzi_tweets.api.config

import com.ada.platzi_tweets.api.models.User
import org.springframework.security.core.Authentication

/**
 * Shorthand for controllers accessing the authenticated user.
 */
fun Authentication.toUser(): User {
    return principal as User
}