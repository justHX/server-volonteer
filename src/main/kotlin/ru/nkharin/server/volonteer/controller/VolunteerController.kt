package ru.nkharin.server.volonteer.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.nkharin.server.volonteer.dto.volonteer.VolunteerDto
import ru.nkharin.server.volonteer.service.VolunteerService

@RestController
class VolunteerController constructor(val serviceVol: VolunteerService) {

    @PostMapping("/volunteer/register")
    fun volunteerRegister (@RequestBody volunteerDto: VolunteerDto) : ResponseEntity<Unit> {
        serviceVol.saveVolunteerData(volunteerDto)
        return ResponseEntity.ok().build()
    }
}