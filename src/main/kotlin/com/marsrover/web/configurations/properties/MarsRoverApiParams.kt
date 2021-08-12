package com.marsrover.web.configurations.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import javax.validation.constraints.NotBlank

/**
 * Data class representing the `api.nasa.mars-rover` `namespace`
 * from the properties files; performs basic validation
 *
 * @property[baseUrl] Base URL to the NASA Mars Rover Photos API
 */
@ConfigurationProperties(prefix = "api.nasa.mars-rover")
@ConstructorBinding
data class MarsRoverApiParams(
    @NotBlank
    val baseUrl: String
)
