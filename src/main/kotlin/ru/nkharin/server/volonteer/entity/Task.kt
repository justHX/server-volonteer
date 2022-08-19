package ru.nkharin.server.volonteer.entity

import java.util.Date

data class Task(val task_id: Long, val isRead: Boolean, val isProcess: Boolean, val completionDate: Date, val startHour: String,
                val endHour: String, val description: String, val checkingCode: String,
                val volunteerId: Long, val userId: Long)
