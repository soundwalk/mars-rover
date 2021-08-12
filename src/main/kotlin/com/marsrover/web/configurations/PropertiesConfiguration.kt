package com.marsrover.web.configurations

import com.marsrover.web.configurations.connections.MarsRoverApiConnection
import com.marsrover.web.configurations.properties.MarsRoverApiParams
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * General application properties configurations
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(value = [MarsRoverApiParams::class])
class PropertiesConfiguration {

    /**
     * @param[marsRoverApiParams] is autowired bean of [MarsRoverApiParams] type
     * @return autoconfigured bean of type [MarsRoverApiConnection]
     */
    @Bean
    fun marsRoverApiConnection(marsRoverApiParams: MarsRoverApiParams): MarsRoverApiConnection =
        MarsRoverApiConnection(
            baseUrl = marsRoverApiParams.baseUrl
        )
}
