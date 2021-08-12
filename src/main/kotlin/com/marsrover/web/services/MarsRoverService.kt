package com.marsrover.web.services

import com.marsrover.web.models.dto.MarsPhotoDto
import com.marsrover.web.models.external.MarsRoverApiResponse
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

@Service
class MarsRoverService(
    private val webClient: WebClient
) {

    /**
     * Fetches [MarsRoverApiResponse.MarsPhoto]s based on the arguments provided in [MarsPhotoDto]
     * Makes one or more calls to the NASA Mars Rover Photos API
     *
     * @param[marsPhotoDto] DTO containing query parameters
     * @return [List] of [MarsRoverApiResponse.MarsPhoto]s
     */
    fun loadPhotos(marsPhotoDto: MarsPhotoDto): List<MarsRoverApiResponse.MarsPhoto> {
        val validCameras = marsPhotoDto.rover.getValidCameras()
        val selectedCameras = marsPhotoDto.selectedCameras
        var apiKey = "DEMO_KEY"

        marsPhotoDto.sol = marsPhotoDto.sol ?: 420

        if (marsPhotoDto.useDemoKey.not()) {
            marsPhotoDto.apiKey = marsPhotoDto.apiKey?.filterNot { it.isWhitespace() }
            if (marsPhotoDto.apiKey?.length == 40) {
                apiKey = marsPhotoDto.apiKey ?: "Should not happen"
            } else {
                marsPhotoDto.apiKey = null
                marsPhotoDto.useDemoKey = true
            }
        }

        return if (
            selectedCameras.any { validCameras.contains(it) } && !validCameras.all { selectedCameras.contains(it) }
        ) {
            mutableListOf<MarsRoverApiResponse.MarsPhoto>().apply {
                validCameras.forEach { camera ->
                    if (selectedCameras.contains(camera))
                        this.addAll(
                            webClient
                                .get()
                                .uri { uriBuilder ->
                                    uriBuilder
                                        .path("/rovers/{rover}/photos")
                                        .queryParam("sol", marsPhotoDto.sol)
                                        .queryParam("camera", camera.name.lowercase())
                                        .queryParam("api_key", apiKey)
                                        .build(marsPhotoDto.rover.name.lowercase())
                                }
                                .accept(MediaType.APPLICATION_JSON)
                                .retrieve()
                                .bodyToMono<MarsRoverApiResponse>()
                                .block()
                                ?.photos ?: throw RuntimeException("Failed to fetch photos")
                        )
                }
            }
        } else
            webClient
                .get()
                .uri { uriBuilder ->
                    uriBuilder
                        .path("/rovers/{rover}/photos")
                        .queryParam("sol", marsPhotoDto.sol)
                        .queryParam("api_key", apiKey)
                        .build(marsPhotoDto.rover.name.lowercase())
                }
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono<MarsRoverApiResponse>()
                .block()
                ?.photos ?: throw RuntimeException("Failed to fetch photos")
    }
}
