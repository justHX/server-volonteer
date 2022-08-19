package ru.nkharin.server.volonteer.service

import org.springframework.stereotype.Service
import ru.nkharin.server.volonteer.dto.volonteer.VolunteerDto
import ru.nkharin.server.volonteer.repository.VolunteerRepo

@Service
class VolunteerService constructor(val volRepo: VolunteerRepo) {

    fun saveVolunteerData (volunteerDto: VolunteerDto) {
        volRepo.saveVolunteer(volunteerDto)
    }

}