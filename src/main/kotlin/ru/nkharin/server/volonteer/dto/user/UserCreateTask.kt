package ru.nkharin.server.volonteer.dto.user

data class UserCreateTask(val taskCompletionDate: String, val startHour: Int, val endHour: Int, val description: String,
            val foodstuff: String, val medicine: String, val essentials: String)
