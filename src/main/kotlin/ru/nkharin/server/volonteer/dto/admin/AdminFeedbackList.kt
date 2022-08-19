package ru.nkharin.server.volonteer.dto.admin

data class AdminFeedbackList(val id: Long, val text: String, val sendDate: String, val isRead: Boolean,
                            val isAnswered: Boolean, val botUser: BotUser)
