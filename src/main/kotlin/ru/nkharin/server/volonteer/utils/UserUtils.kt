package ru.nkharin.server.volonteer.utils

import ru.nkharin.server.volonteer.dto.user.UserLoginDto
import ru.nkharin.server.volonteer.entity.Users

class UserUtils {

    companion object {
        fun checkUser(userList: List<Users>, userDto: UserLoginDto): Users? {
            for (user in userList) {
                if (user.login == userDto.email && user.password == userDto.password)
                    return user
            }
            return null
        }
    }
}