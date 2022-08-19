package ru.nkharin.server.volonteer.repository.impl

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.nkharin.server.volonteer.dto.user.UserCreateTask
import ru.nkharin.server.volonteer.entity.Task
import ru.nkharin.server.volonteer.repository.TaskRepo
import java.sql.ResultSet
import java.time.LocalDate
import java.util.UUID

@Repository
class TaskRepoImpl constructor(val jdbcTemplate: JdbcTemplate) : TaskRepo {

    override fun takeTaskListIdUser(userId: Long): List<Task> {
        val taskList =
            jdbcTemplate.query("SELECT * FROM task where user_id = " + userId) { resultSet: ResultSet, _: Int ->
                Task(
                    resultSet.getLong("task_id"), resultSet.getBoolean("is_read"),
                    resultSet.getBoolean("is_process"), resultSet.getDate("completion_date"),
                    resultSet.getString("start_hour"), resultSet.getString("end_hour"),
                    resultSet.getString("description"), resultSet.getString("checking_code"),
                    resultSet.getLong("volunteer_id"), resultSet.getLong("user_id")
                )
            }
        return taskList
    }

    override fun takeTaskById(taskId: Long): Task {
        val task = jdbcTemplate.query("SELECT * FROM task where task_id = " + taskId) { resultSet: ResultSet, _: Int ->
            Task(
                resultSet.getLong("task_id"), resultSet.getBoolean("is_read"),
                resultSet.getBoolean("is_process"), resultSet.getDate("completion_date"),
                resultSet.getString("start_hour"), resultSet.getString("end_hour"),
                resultSet.getString("description"), resultSet.getString("checking_code"),
                resultSet.getLong("volunteer_id"), resultSet.getLong("user_id")
            )
        }
        return task.first()
    }

    override fun createTask(userCreateTask: UserCreateTask, userId: Long): String {

        val code = UUID.randomUUID()

        jdbcTemplate.update(
            "INSERT INTO task (is_read, is_process, completion_date, start_hour, end_hour, " +
                    "description, checking_code, volunteer_id, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
            false, true, LocalDate.parse(userCreateTask.taskCompletionDate), userCreateTask.startHour.toString(),
            userCreateTask.endHour.toString(), userCreateTask.description, code.toString(), 1, userId
        )

        return code.toString()
    }

    override fun takeTaskIdByUuid(uuid: String): Long {
        val task =
            jdbcTemplate.query("SELECT * FROM task where checking_code = '" + uuid + "'") { resultSet: ResultSet, _: Int ->
                Task(
                    resultSet.getLong("task_id"), resultSet.getBoolean("is_read"),
                    resultSet.getBoolean("is_process"), resultSet.getDate("completion_date"),
                    resultSet.getString("start_hour"), resultSet.getString("end_hour"),
                    resultSet.getString("description"), resultSet.getString("checking_code"),
                    resultSet.getLong("volunteer_id"), resultSet.getLong("user_id")
                )
            }
        return task.first().task_id
    }
}