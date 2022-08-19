package ru.nkharin.server.volonteer.entity

import java.util.Date

data class Volunteer(val volunteerId: Long, val name: String, val age: Int, val experience: Int,
                     val tgChat: String, val phone: String, val gender: String, val employedDate: Date, val district: String, val street: String,
                     val house: String, val flat: String)
