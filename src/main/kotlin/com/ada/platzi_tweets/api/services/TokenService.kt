package com.ada.platzi_tweets.api.services

import com.ada.platzi_tweets.api.models.User
import org.springframework.security.oauth2.jwt.JwsHeader
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.logging.Level
import java.util.logging.Logger

@Service
class TokenService(
        private val jwtDecoder: JwtDecoder,
        private val jwtEncoder: JwtEncoder,
        private val userService: UserService
) {
    fun createToken(user: User): String {
        val jwsHeader = JwsHeader.with { "HS256" }.build()
        val claims = JwtClaimsSet.builder()
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plus(30L, ChronoUnit.DAYS))
                .subject(user.email)
                .claim("userId", user.id)
                .claim("email", user.email)
                .build()

        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).tokenValue
    }

    fun parseToken(token: String): User? {
        return try {
            val jwt = jwtDecoder.decode(token)
            val userId = jwt.claims["userId"] as Long
            userService.findById(userId)
        } catch (e: Exception){
            Logger.getLogger("TokenService")?.log(Level.WARNING, "Cannot parse JWT token")
            null
        }
    }
}