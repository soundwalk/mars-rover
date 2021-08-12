package com.marsrover.web

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.time.LocalDateTime
import java.util.TimeZone

@SpringBootApplication
class WebApplication

fun main(args: Array<String>) {
    runApplication<WebApplication>(*args)

    TimeZone.setDefault(TimeZone.getTimeZone("Europe/Zagreb"))
    println(
        """
        Application started successfully!
        Datetime: ${LocalDateTime.now()}
        """.trimIndent()
    )
}
