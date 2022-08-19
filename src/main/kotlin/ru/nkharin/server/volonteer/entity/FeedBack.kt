package ru.nkharin.server.volonteer.entity

import java.util.Date

data class FeedBack(val feedId: Long, val readDate: Date, val text: String, val sendDate: Date, val isRead: Boolean,
                    val isAnswered: Boolean, val answerText: String, val answerDate: String, val volunteerId: Int)
