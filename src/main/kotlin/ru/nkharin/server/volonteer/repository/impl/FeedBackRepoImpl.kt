package ru.nkharin.server.volonteer.repository.impl

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.nkharin.server.volonteer.entity.FeedBack
import ru.nkharin.server.volonteer.repository.FeedbackRepo
import java.sql.ResultSet

@Repository
class FeedBackRepoImpl constructor(val jdbcTemplate: JdbcTemplate) : FeedbackRepo{
    override fun takeFeedbackList(): List<FeedBack> {
        return jdbcTemplate.query("SELECT * FROM feed_back") { resultSet: ResultSet, _: Int ->
            FeedBack(
                resultSet.getLong("feed_id"), resultSet.getDate("read_date"),
                resultSet.getString("text"), resultSet.getDate("send_date"),
                resultSet.getBoolean("is_read"), resultSet.getBoolean("is_answered"),
                resultSet.getString("answer_text"), resultSet.getString("answer_date"),
                resultSet.getInt("volunteer_id")
            )
        }
    }

    override fun takeFeedbackById(id: Long): FeedBack {
        val feedBack = jdbcTemplate.query("SELECT * FROM feed_back WHERE feed_id = "+ id) { resultSet: ResultSet, _: Int ->
            FeedBack(
                resultSet.getLong("feed_id"), resultSet.getDate("read_date"),
                resultSet.getString("text"), resultSet.getDate("send_date"),
                resultSet.getBoolean("is_read"), resultSet.getBoolean("is_answered"),
                resultSet.getString("answer_text"), resultSet.getString("answer_date"),
                resultSet.getInt("volunteer_id")
            )
        }
        return feedBack.first()
    }

    override fun updateFeedbackText(id: Long, text: String) {
        jdbcTemplate.update(
            "UPDATE feed_back SET text = ? WHERE feed_id = ?", text, id)
    }
}