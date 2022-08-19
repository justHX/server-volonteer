package ru.nkharin.server.volonteer.dto.admin

data class AdminFeedbackInfo(val id: Long, val text: String, val sendDate: String, val isRead: Boolean,
                             val isAnswered: Boolean, val volunteer: AdminVolunteer, val readDate: String,
                             val answerText: String, val answerDate: String)
