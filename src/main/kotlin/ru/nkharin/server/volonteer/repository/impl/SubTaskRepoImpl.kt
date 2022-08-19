package ru.nkharin.server.volonteer.repository.impl

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.nkharin.server.volonteer.dto.user.UserCreateTask
import ru.nkharin.server.volonteer.entity.SubTask
import ru.nkharin.server.volonteer.repository.SubTaskRepo
import java.sql.ResultSet

@Repository
class SubTaskRepoImpl constructor(val jdbcTemplate: JdbcTemplate) : SubTaskRepo{
    override fun takeSubTasks(taskId: Long): List<SubTask> {
        val subTaskList = jdbcTemplate.query("SELECT * FROM sub_task where task_id = " + taskId) { resultSet: ResultSet, _: Int ->
            SubTask(
                resultSet.getLong("sub_id"), resultSet.getString("title"),
                resultSet.getString("type"), resultSet.getLong("task_id")
            )
        }
        return subTaskList
    }

    override fun createSubtask(userCreateTask: UserCreateTask, idTask: Long) {
        if(userCreateTask.foodstuff.isNotBlank()){
            jdbcTemplate.update("INSERT INTO sub_task (title, type, task_id) VALUES (?, ?, ?)",
                "Foodstuff", userCreateTask.foodstuff, idTask
            )
        }

        if(userCreateTask.medicine.isNotBlank()){
            jdbcTemplate.update("INSERT INTO sub_task (title, type, task_id) VALUES (?, ?, ?)",
                "Medicine", userCreateTask.medicine, idTask
            )
        }

        if(userCreateTask.essentials.isNotBlank()){
            jdbcTemplate.update("INSERT INTO sub_task (title, type, task_id) VALUES (?, ?, ?)",
                "Essentials", userCreateTask.essentials, idTask
            )
        }
    }
}