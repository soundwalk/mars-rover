package com.marsrover.web.models.dto

import com.marsrover.web.models.external.MarsCameraType

/**
 * [MarsPhotoDto] acts as a DTO for search queries
 *
 * @property[rover] Rover name (select list)
 * @property[sol] Sol (non-negative number)
 * @property[selectedCameras] List of selected rover cameras
 * @property[useDemoKey] Use demo key toggle switch
 * @property[apiKey] NASA Mars Rover (Photos) API key
 */
data class MarsPhotoDto(
    val rover: MarsRover = MarsRover(),
    var sol: Long? = null,
    val selectedCameras: List<MarsCameraType> = mutableListOf(),
    var useDemoKey: Boolean = true,
    var apiKey: String? = null
) {

    data class MarsRover(
        var name: String = "spirit"
    ) {
        fun getValidCameras(): List<MarsCameraType> =
            when (name.lowercase()) {
                "curiosity" -> listOf(
                    MarsCameraType.FHAZ,
                    MarsCameraType.RHAZ,
                    MarsCameraType.MAST,
                    MarsCameraType.CHEMCAM,
                    MarsCameraType.MAHLI,
                    MarsCameraType.MARDI,
                    MarsCameraType.NAVCAM
                )
                "opportunity", "spirit" -> listOf(
                    MarsCameraType.FHAZ,
                    MarsCameraType.RHAZ,
                    MarsCameraType.NAVCAM,
                    MarsCameraType.PANCAM,
                    MarsCameraType.MINITES
                )
                else -> throw RuntimeException("Invalid rover name")
            }
    }
}
