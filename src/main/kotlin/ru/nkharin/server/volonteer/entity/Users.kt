package ru.nkharin.server.volonteer.entity

data class Users(val userId: Long, val name: String, val phone: String, val age: Int, val district: String,
    val street: String, val house: String, val flat: String, val login: String, val password: String)
