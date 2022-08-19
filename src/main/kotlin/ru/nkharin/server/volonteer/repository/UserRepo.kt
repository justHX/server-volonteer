package ru.nkharin.server.volonteer.repository

import ru.nkharin.server.volonteer.dto.admin.AdminUserInfo
import ru.nkharin.server.volonteer.dto.user.UserInfo
import ru.nkharin.server.volonteer.dto.user.UserRegisterDto
import ru.nkharin.server.volonteer.entity.Users

interface UserRepo {

    fun takeUsersList(): List<Users>
    fun takeUserById(id: Long): Users
    fun saveUser (userRegisterDto: UserRegisterDto) : Int
    fun updateUser (userInfo: UserInfo) : Int
    fun updateUser(adminUserInfo: AdminUserInfo)
    fun deleteUser(id: Long)
}