package ru.nkharin.server.volonteer.dto.admin

data class AdminVolunteerDto(val id: Long, val name: String, val phone: String,
                             val gender: String, val employedDate: String, val isRegister: Boolean)
