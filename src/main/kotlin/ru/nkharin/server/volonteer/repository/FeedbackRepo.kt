package ru.nkharin.server.volonteer.repository

import ru.nkharin.server.volonteer.entity.FeedBack

interface FeedbackRepo {
    fun takeFeedbackList(): List<FeedBack>
    fun takeFeedbackById(id: Long): FeedBack
    fun updateFeedbackText(id: Long, text: String)
}