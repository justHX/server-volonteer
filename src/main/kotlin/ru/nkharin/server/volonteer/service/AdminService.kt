package ru.nkharin.server.volonteer.service

import org.springframework.stereotype.Service
import ru.nkharin.server.volonteer.dto.admin.*
import ru.nkharin.server.volonteer.dto.volonteer.VolunteerUpdateDto
import ru.nkharin.server.volonteer.repository.AdminRepo
import ru.nkharin.server.volonteer.repository.FeedbackRepo
import ru.nkharin.server.volonteer.repository.UserRepo
import ru.nkharin.server.volonteer.repository.VolunteerRepo
import ru.nkharin.server.volonteer.utils.AdminUtils

@Service
class AdminService(
    val adminRepo: AdminRepo, val feedbackRepo: FeedbackRepo,
    val volunteerRepo: VolunteerRepo, val userRepo: UserRepo
) {

    fun checkLoginAdmin(adminDto: AdminLoginDto): AdminLoginResp? {
        val takeAdminList = adminRepo.takeAdminList(adminDto.email, adminDto.password)

        val checkAdmin = AdminUtils.checkAdmin(takeAdminList, adminDto)

        return if (checkAdmin != null)
            AdminLoginResp(checkAdmin.id, checkAdmin.login, checkAdmin.fullName)
        else null
    }

    fun takeFeedbackList(): List<AdminFeedbackList> {
        val feedbackList = feedbackRepo.takeFeedbackList()
        val adminFeedbackList = mutableListOf<AdminFeedbackList>()
        for (feedback in feedbackList) {
            adminFeedbackList.add(
                AdminFeedbackList(
                    feedback.feedId, feedback.text, feedback.sendDate.toString(),
                    feedback.isRead, feedback.isAnswered, BotUser("1", "name", 18, 3)
                )
            )
        }
        return adminFeedbackList
    }

    fun takeFeedbackInfo(id: Long): AdminFeedbackInfo {
        val feedBack = feedbackRepo.takeFeedbackById(id)

        val volunteer = volunteerRepo.takeVolunteerById(feedBack.volunteerId.toLong())
        return AdminFeedbackInfo(
            feedBack.feedId, feedBack.text, feedBack.sendDate.toString(), feedBack.isRead,
            feedBack.isAnswered, AdminVolunteer(volunteer.volunteerId.toString(), volunteer.name),
            feedBack.readDate.toString(), feedBack.answerText, feedBack.answerDate
        )
    }

    fun updateFeedbackText(id: Long, text: String) = feedbackRepo.updateFeedbackText(id, text)

    fun takeVolonteerList(): List<AdminVolunteerDto> {
        val takeVolunteerList = volunteerRepo.takeVolunteerList()
        val adminVolunteerList = mutableListOf<AdminVolunteerDto>()

        for (volunteer in takeVolunteerList) {
            adminVolunteerList.add(
                AdminVolunteerDto(
                    volunteer.volunteerId, volunteer.name, volunteer.phone,
                    volunteer.gender, volunteer.employedDate.toString(), true
                )
            )
        }

        return adminVolunteerList
    }

    fun takeVolunteerById(id: Long): AdminVolunteerDto {
        val volunteer = volunteerRepo.takeVolunteerById(id)

        return AdminVolunteerDto(
            volunteer.volunteerId, volunteer.name, volunteer.phone, volunteer.gender,
            volunteer.employedDate.toString(), true
        )
    }

    fun updateVolunteer(volunteerUpdateDto: VolunteerUpdateDto) = volunteerRepo.updateVolunteer(volunteerUpdateDto)

    fun deleteVolunteer(id: Long) = volunteerRepo.deleteVolunteer(id)

    fun takeUserList(): List<AdminUser> {
        val usersList = userRepo.takeUsersList()
        val adminUserList = mutableListOf<AdminUser>()

        for (user in usersList) {
            adminUserList.add(AdminUser(user.userId.toString(), user.name, user.phone, user.street, user.house))
        }

        return adminUserList
    }

    fun takeUserById(id: Long): AdminUserInfo {
        val user = userRepo.takeUserById(id)

        return AdminUserInfo(user.userId, user.name, user.age, user.phone, user.district, user.street,
        user.house, user.flat)
    }

    fun updateUser(adminUserInfo: AdminUserInfo) = userRepo.updateUser(adminUserInfo)

    fun deleteUser(id: Long) = userRepo.deleteUser(id)
}