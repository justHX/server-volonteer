package ru.nkharin.server.volonteer.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.nkharin.server.volonteer.dto.user.*
import ru.nkharin.server.volonteer.service.UserService

@RestController
class UserController(val userService: UserService) {

    @PostMapping("/user/login")
    fun loginUser(@RequestBody userLogin: UserLoginDto): ResponseEntity<UserResp> {

        val checkLoginUser = userService.checkLoginUser(userLogin)

        return if (checkLoginUser == null)
            ResponseEntity.badRequest().build()
        else
            ResponseEntity.ok(checkLoginUser)
    }

    @PostMapping("/user/register")
    fun userRegister(@RequestBody userRegister: UserRegisterDto): ResponseEntity<Int> =
        ResponseEntity.ok(userService.saveUser(userRegister))

    @GetMapping("/user/tasks")
    fun userTasks(@RequestParam id: Long): ResponseEntity<List<UserTasks>> =
        ResponseEntity.ok(userService.takeTaskList(id))

    @GetMapping("/user/task")
    fun userTask(@RequestParam id: Long): ResponseEntity<UserTask> =
        ResponseEntity.ok(userService.takeTask(id))

    @GetMapping("/user/info")
    fun userInfo(@RequestParam id: Long): ResponseEntity<UserInfo> =
        ResponseEntity.ok(userService.takeUserInfo(id))

    @PostMapping("/user/update")
    fun userUpdate(@RequestBody userInfo: UserInfo): ResponseEntity<Int> =
        ResponseEntity.ok(userService.userUpdate(userInfo))

    @PostMapping("/user/createTaskInfo")
    fun userCreateTask(@RequestParam id: Long, @RequestBody userCreateTask: UserCreateTask): ResponseEntity<String> =
        ResponseEntity.ok(userService.userCreateTask(userCreateTask, id))
}