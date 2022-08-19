package ru.nkharin.server.volonteer.dto.volonteer

data class VolunteerUpdateDto(val id: String, val name: String, val phone: String, val gender: String,
val employedDate: String, val isRegister: Boolean)
