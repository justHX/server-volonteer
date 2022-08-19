package ru.nkharin.server.volonteer.dto.user

data class UserInfo(val userId: Long, val name: String,  val age: Int, val phone: String, val district: String,
                    val street: String, val house: String, val flat: String, val email: String, val password: String)
