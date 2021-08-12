package com.marsrover.web.api

import com.marsrover.web.models.dto.MarsPhotoDto
import com.marsrover.web.services.MarsRoverService
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute

/**
 * Home/main controller
 */
@Controller
class HomeController(
    private val marsRoverService: MarsRoverService
) {

    @GetMapping
    fun home(
        @ModelAttribute marsPhotoDto: MarsPhotoDto,
        modelMap: ModelMap
    ): String {
        modelMap["photos"] = marsRoverService.loadPhotos(marsPhotoDto)
        modelMap["marsPhotoDto"] = marsPhotoDto
        return "index"
    }
}
