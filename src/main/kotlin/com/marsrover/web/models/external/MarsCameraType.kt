package com.marsrover.web.models.external

/**
 * Helper enum class for camera abbreviations as well as their full names
 *
 * @property[fullName] Mars Rover camera's full name
 */
enum class MarsCameraType(val fullName: String) {
    FHAZ("Front Hazard Avoidance Camera"),
    RHAZ("Rear Hazard Avoidance Camera"),
    MAST("Mast Camera"),
    CHEMCAM("Chemistry and Camera Complex"),
    MAHLI("Mars Hand Lens Imager"),
    MARDI("Mars Descent Imager"),
    NAVCAM("Navigation Camera"),
    PANCAM("Panoramic Camera"),
    MINITES("Miniature Thermal Emission Spectrometer (Mini-TES)")
}
