package ru.nkharin.server.volonteer.utils

import ru.nkharin.server.volonteer.dto.admin.AdminLoginDto
import ru.nkharin.server.volonteer.entity.Admins

class AdminUtils {
    companion object {
        fun checkAdmin(adminList: List<Admins>, adminDto: AdminLoginDto): Admins? {
            for (admin in adminList) {
                if (admin.login == adminDto.email && admin.password == adminDto.password)
                    return admin
            }
            return null
        }
    }
}