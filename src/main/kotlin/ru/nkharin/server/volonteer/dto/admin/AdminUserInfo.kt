package ru.nkharin.server.volonteer.dto.admin

data class AdminUserInfo(val userId: Long, val name: String,  val age: Int, val phone: String, val district: String,
                         val street: String, val house: String, val flat: String)
