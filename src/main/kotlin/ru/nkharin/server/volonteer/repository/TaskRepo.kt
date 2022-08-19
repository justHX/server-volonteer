package ru.nkharin.server.volonteer.repository

import ru.nkharin.server.volonteer.dto.user.UserCreateTask
import ru.nkharin.server.volonteer.entity.Task

interface TaskRepo {
    fun takeTaskListIdUser(userId: Long): List<Task>
    fun takeTaskById(taskId: Long): Task
    fun createTask(userCreateTask: UserCreateTask, userId: Long): String
    fun takeTaskIdByUuid(uuid: String): Long
}