package ru.nkharin.server.volonteer.dto.volonteer

data class VolunteerDto(val name: String, val age: Int, val experience: Int,
                        val phone: String, val gender: String, val district: String, val street: String,
                        val house: String, val flat: String)
