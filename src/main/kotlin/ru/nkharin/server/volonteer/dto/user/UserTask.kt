package ru.nkharin.server.volonteer.dto.user

import ru.nkharin.server.volonteer.entity.SubTask

data class UserTask(val id: Long, val name: String, val taskCompletionDate: String,
                    val street: String, val house: String, val age: Int, val phone: String,
                    val flat: String, val volunteerName: String, val volunteerAge: String,
                    val volunteerPhone: String, val isReady: Boolean, val inProcess: Boolean,
                    val startHour: String, val endHour: String, val description: String, val subTasks: List<SubTask>)
