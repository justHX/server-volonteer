package ru.nkharin.server.volonteer.service

import org.springframework.stereotype.Service
import ru.nkharin.server.volonteer.dto.user.*
import ru.nkharin.server.volonteer.repository.SubTaskRepo
import ru.nkharin.server.volonteer.repository.TaskRepo
import ru.nkharin.server.volonteer.repository.UserRepo
import ru.nkharin.server.volonteer.repository.VolunteerRepo
import ru.nkharin.server.volonteer.utils.UserUtils

@Service
class UserService(
    val userRepo: UserRepo, val taskRepo: TaskRepo,
    val volunteerRepo: VolunteerRepo, val subTaskRepo: SubTaskRepo
) {

    fun checkLoginUser(userDto: UserLoginDto): UserResp? {
        val takeUserList = userRepo.takeUsersList()

        val checkUser = UserUtils.checkUser(takeUserList, userDto)

        return if (checkUser != null)
            UserResp(checkUser.userId.toString())
        else null
    }

    fun saveUser(userRegister: UserRegisterDto): Int = userRepo.saveUser(userRegister)

    fun takeTaskList(id: Long): List<UserTasks> {
        val takeTaskListIdUser = taskRepo.takeTaskListIdUser(id)

        val listTask = mutableListOf<UserTasks>()

        for (task in takeTaskListIdUser) {
            val takeVolunteerById = volunteerRepo.takeVolunteerById(task.volunteerId)
            listTask.add(
                UserTasks(
                    task.task_id, takeVolunteerById.name, task.completionDate.toString(),
                    takeVolunteerById.street, takeVolunteerById.house
                )
            )
        }
        return listTask
    }

    fun takeTask(id: Long): UserTask {
        val takeTaskById = taskRepo.takeTaskById(id)
        val takeUserById = userRepo.takeUserById(takeTaskById.userId)
        val takeVolunteerById = volunteerRepo.takeVolunteerById(takeTaskById.volunteerId)

        val subTaskList = subTaskRepo.takeSubTasks(id)

        return UserTask(
            takeTaskById.task_id, takeUserById.name, takeTaskById.completionDate.toString(),
            takeUserById.street, takeUserById.house, takeUserById.age, takeUserById.phone, takeUserById.flat,
            takeVolunteerById.name, takeVolunteerById.age.toString(), takeVolunteerById.phone, takeTaskById.isRead,
            takeTaskById.isProcess, takeTaskById.startHour, takeTaskById.endHour, takeTaskById.description, subTaskList)
    }

    fun takeUserInfo(id: Long) : UserInfo {
        val user = userRepo.takeUserById(id)

        return UserInfo(user.userId, user.name, user.age, user.phone, user.district, user.street, user.house, user.flat,
        user.login, user.password)
    }

    fun userUpdate(userInfo: UserInfo): Int = userRepo.updateUser(userInfo)

    fun userCreateTask(userCreateTask: UserCreateTask, id: Long) : String{
        val uuid = taskRepo.createTask(userCreateTask, id)
        val taskId = taskRepo.takeTaskIdByUuid(uuid)

        subTaskRepo.createSubtask(userCreateTask, taskId)
        return uuid
    }
}