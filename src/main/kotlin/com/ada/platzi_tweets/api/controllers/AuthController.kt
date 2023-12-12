package com.ada.platzi_tweets.api.controllers

import com.ada.platzi_tweets.api.dtos.LoginDto
import com.ada.platzi_tweets.api.dtos.LoginResponseDto
import com.ada.platzi_tweets.api.dtos.RegisterDto
import com.ada.platzi_tweets.api.models.User
import com.ada.platzi_tweets.api.services.HashService
import com.ada.platzi_tweets.api.services.TokenService
import com.ada.platzi_tweets.api.services.UserService
import org.apache.coyote.BadRequestException
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class AuthController (
        private val hashService: HashService,
        private val tokenService: TokenService,
        private val userService: UserService
) {
    @PostMapping("/login")
    fun login(@RequestBody payload: LoginDto): LoginResponseDto {
        val user = userService.findByEmail(payload.email) ?: throw BadRequestException("Login failed")

        if (!hashService.checkBcrypt(payload.password, user.password)) {
            throw NotFoundException()
        }

        return LoginResponseDto(
            user = user,
            token = tokenService.createToken(user),
        )
    }

    @PostMapping("/register")
    fun register(@RequestBody payload: RegisterDto): LoginResponseDto {

        val user = User(
                names = payload.names,
                email = payload.email,
                password = hashService.hashBcrypt(payload.password),
        )

        val savedUser = userService.save(user)

        return LoginResponseDto(
                user = user,
                token = tokenService.createToken(savedUser),
        )
    }
}