package ru.nkharin.server.volonteer.repository

import ru.nkharin.server.volonteer.dto.user.UserCreateTask
import ru.nkharin.server.volonteer.entity.SubTask

interface SubTaskRepo {
    fun takeSubTasks(taskId: Long): List<SubTask>
    fun createSubtask(userCreateTask: UserCreateTask, idTask: Long)
}