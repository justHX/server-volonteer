package ru.nkharin.server.volonteer.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.nkharin.server.volonteer.dto.admin.*
import ru.nkharin.server.volonteer.dto.volonteer.VolunteerUpdateDto
import ru.nkharin.server.volonteer.entity.Volunteer
import ru.nkharin.server.volonteer.service.AdminService

@RestController
class AdminController(val adminService: AdminService) {

    @PostMapping("admin/login")
    fun login(@RequestBody loginDto: AdminLoginDto): ResponseEntity<AdminLoginResp?> {

        val checkLoginAdmin = adminService.checkLoginAdmin(loginDto)

        return if (checkLoginAdmin == null)
            ResponseEntity.badRequest().build()
        else
            ResponseEntity.ok(checkLoginAdmin)
    }

    @GetMapping("/admin/feedback/list")
    fun adminFeedbackList(): ResponseEntity<List<AdminFeedbackList>> =
        ResponseEntity.ok(adminService.takeFeedbackList())

    @GetMapping("/admin/feedback/info")
    fun adminFeedbackInfo(@RequestParam id: Long): ResponseEntity<AdminFeedbackInfo> =
        ResponseEntity.ok(adminService.takeFeedbackInfo(id))

    @PostMapping("/admin/feedback/send/answer")
    fun adminFeedbackSendAnswer(@RequestParam id: Long, @RequestBody textDto: TextDto): ResponseEntity<Unit> =
        ResponseEntity.ok(adminService.updateFeedbackText(id, textDto.text))

    @GetMapping("admin/volunteer/list")
    fun volunteerList(): ResponseEntity<List<AdminVolunteerDto>> = ResponseEntity.ok(adminService.takeVolonteerList())

    @GetMapping("admin/volunteer/info")
    fun volunteerInfo(@RequestParam id: Long) : ResponseEntity<AdminVolunteerDto> =
        ResponseEntity.ok(adminService.takeVolunteerById(id))

    @PostMapping("/admin/volunteer/update")
    fun adminUpdateVolunteer(@RequestBody volunteerUpdateDto: VolunteerUpdateDto): ResponseEntity<Unit> =
        ResponseEntity.ok(adminService.updateVolunteer(volunteerUpdateDto))

    @PostMapping("/admin/volunteer/delete")
    fun adminDeleteVolunteer(@RequestParam id: Long): ResponseEntity<Unit> =
        ResponseEntity.ok(adminService.deleteVolunteer(id))

    @GetMapping("admin/user/list")
    fun userList(): ResponseEntity<List<AdminUser>> = ResponseEntity.ok(adminService.takeUserList())

    @GetMapping("admin/user/info")
    fun userInfo(@RequestParam id: Long) : ResponseEntity<AdminUserInfo> =
        ResponseEntity.ok(adminService.takeUserById(id))

    @PostMapping("/admin/user/update")
    fun adminUpdateUser(@RequestBody adminUserInfo: AdminUserInfo): ResponseEntity<Unit> =
        ResponseEntity.ok(adminService.updateUser(adminUserInfo))

    @PostMapping("/admin/user/delete")
    fun adminDeleteUser(@RequestParam id: Long): ResponseEntity<Unit> =
        ResponseEntity.ok(adminService.deleteUser(id))
}