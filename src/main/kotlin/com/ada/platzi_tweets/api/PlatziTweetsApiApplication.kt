package com.ada.platzi_tweets.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import io.github.cdimascio.dotenv.Dotenv

@SpringBootApplication
class PlatziTweetsApiApplication

fun main(args: Array<String>) {
    try {
        val dotenv = Dotenv.load()
        dotenv.entries().forEach { entry -> System.setProperty(entry.key, entry.value) }
    } catch (_: Exception) { }

    runApplication<PlatziTweetsApiApplication>(*args)
}
