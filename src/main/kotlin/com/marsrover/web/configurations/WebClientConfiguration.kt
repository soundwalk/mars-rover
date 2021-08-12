package com.marsrover.web.configurations

import com.marsrover.web.configurations.connections.MarsRoverApiConnection
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient

/**
 * WebClient configuration
 * Note: application connects to single external API making this configuration more than sufficient
 */
@Configuration
class WebClientConfiguration(
    val marsRoverApiConnection: MarsRoverApiConnection
) {

    /**
     * @param[webclientBuilder] used to configure the [WebClient]
     * @return [WebClient] bean configured to connect to NASA Mars Rover Photos API
     */
    @Bean
    fun webClient(webclientBuilder: WebClient.Builder): WebClient =
        webclientBuilder
            .baseUrl(marsRoverApiConnection.baseUrl)
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .build()
}
