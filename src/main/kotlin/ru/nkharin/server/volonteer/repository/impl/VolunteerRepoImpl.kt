package ru.nkharin.server.volonteer.repository.impl

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.nkharin.server.volonteer.dto.volonteer.VolunteerDto
import ru.nkharin.server.volonteer.dto.volonteer.VolunteerUpdateDto
import ru.nkharin.server.volonteer.entity.Volunteer
import ru.nkharin.server.volonteer.repository.VolunteerRepo
import java.sql.ResultSet
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.OffsetDateTime

@Repository
class VolunteerRepoImpl constructor(val jdbcTemplate: JdbcTemplate) : VolunteerRepo {

    override fun saveVolunteer(volunteerDto: VolunteerDto) {
        jdbcTemplate.update(
            "INSERT INTO volunteer (name, age, experience, tg_chat, phone, " +
                    "district, street, house, flat) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
            volunteerDto.name, volunteerDto.age, volunteerDto.experience, "telegram", volunteerDto.phone,
            volunteerDto.gender, LocalDateTime. now(), volunteerDto.district, volunteerDto.street,
            volunteerDto.house, volunteerDto.flat
        )
    }

    override fun takeVolunteerById(id: Long): Volunteer {
        val volunteer = jdbcTemplate.query("SELECT * FROM volunteer where volunteer_id = " + id) { resultSet: ResultSet, _: Int ->
            Volunteer(
                resultSet.getLong("volunteer_id"), resultSet.getString("name"),
                resultSet.getInt("age"), resultSet.getInt("experience"),
                resultSet.getString("tg_chat"), resultSet.getString("phone"),
                resultSet.getString("gender"), resultSet.getDate("employed_date"),
                resultSet.getString("district"), resultSet.getString("street"),
                resultSet.getString("house"), resultSet.getString("flat")
            )
        }
        return volunteer.first()
    }

    override fun takeVolunteerList(): List<Volunteer> {
       return jdbcTemplate.query("SELECT * FROM volunteer") { resultSet: ResultSet, _: Int ->
            Volunteer(
                resultSet.getLong("volunteer_id"), resultSet.getString("name"),
                resultSet.getInt("age"), resultSet.getInt("experience"),
                resultSet.getString("tg_chat"), resultSet.getString("phone"),
                resultSet.getString("gender"), resultSet.getDate("employed_date"),
                resultSet.getString("district"), resultSet.getString("street"),
                resultSet.getString("house"), resultSet.getString("flat")
            )
        }
    }

    override fun updateVolunteer(volunteerUpdateDto: VolunteerUpdateDto) {
        jdbcTemplate.update(
            "UPDATE volunteer SET name = ?, phone = ?, gender = ?, employed_date = ? WHERE volunteer_id = ?",
            volunteerUpdateDto.name, volunteerUpdateDto.phone, volunteerUpdateDto.gender,
            OffsetDateTime.parse(volunteerUpdateDto.employedDate), volunteerUpdateDto.id.toLong())
    }

    override fun deleteVolunteer(id: Long) {
        jdbcTemplate.update("DELETE FROM task WHERE volunteer_id = " + id)
        jdbcTemplate.update("DELETE FROM feed_back WHERE volunteer_id = " + id)
        jdbcTemplate.update("DELETE FROM volunteer WHERE volunteer_id = " + id)
    }

}