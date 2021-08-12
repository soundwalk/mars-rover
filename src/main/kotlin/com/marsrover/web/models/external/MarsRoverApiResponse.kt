package com.marsrover.web.models.external

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

/**
 * Maps the attributes from the NASA Mars Rover Photos API response
 *
 * @property[photos] contains the list of [MarsPhoto] objects
 */
data class MarsRoverApiResponse(
    @JsonProperty("photos")
    val photos: List<MarsPhoto>
) {
    data class MarsPhoto(
        @JsonProperty("id")
        val id: Long,
        @JsonProperty("sol")
        val sol: Int,
        @JsonProperty("camera")
        val camera: MarsCamera,
        @JsonProperty("img_src")
        val imgSrc: String,
        @JsonProperty("earth_date")
        val earthDate: LocalDate,
        @JsonProperty("rover")
        val rover: MarsRover
    ) {

        data class MarsCamera(
            @JsonProperty("id")
            val id: Long,
            @JsonProperty("name")
            val name: String,
            @JsonProperty("rover_id")
            val roverId: Long,
            @JsonProperty("full_name")
            val fullName: String
        )

        data class MarsRover(
            @JsonProperty("id")
            val id: Long,
            @JsonProperty("name")
            val name: String,
            @JsonProperty("landing_date")
            val landingDate: LocalDate,
            @JsonProperty("launch_date")
            val launchDate: LocalDate
        )
    }
}
