package ru.nkharin.server.volonteer.dto.user

data class UserTasks(val id: Long, val name: String, val taskCompletionDate: String,
                     val street: String, val house: String)
