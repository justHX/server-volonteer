package ru.nkharin.server.volonteer.repository

import ru.nkharin.server.volonteer.dto.volonteer.VolunteerDto
import ru.nkharin.server.volonteer.dto.volonteer.VolunteerUpdateDto
import ru.nkharin.server.volonteer.entity.Volunteer

interface VolunteerRepo {
    fun saveVolunteer(volunteerDto: VolunteerDto)
    fun takeVolunteerById(id: Long) : Volunteer
    fun takeVolunteerList(): List<Volunteer>
    fun updateVolunteer(volunteerUpdateDto: VolunteerUpdateDto)
    fun deleteVolunteer(id: Long)
}