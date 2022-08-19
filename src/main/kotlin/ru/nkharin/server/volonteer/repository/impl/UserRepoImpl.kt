package ru.nkharin.server.volonteer.repository.impl

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.nkharin.server.volonteer.dto.admin.AdminUserInfo
import ru.nkharin.server.volonteer.dto.user.UserInfo
import ru.nkharin.server.volonteer.dto.user.UserRegisterDto
import ru.nkharin.server.volonteer.dto.volonteer.VolunteerUpdateDto
import ru.nkharin.server.volonteer.entity.Users
import ru.nkharin.server.volonteer.repository.UserRepo
import java.sql.ResultSet
import java.time.OffsetDateTime

@Repository
class UserRepoImpl constructor(val jdbcTemplate: JdbcTemplate) : UserRepo {

    override fun takeUsersList(): List<Users> {
        val userList = jdbcTemplate.query("SELECT * FROM users") { resultSet: ResultSet, _: Int ->
            Users(
                resultSet.getLong("user_id"), resultSet.getString("name"),
                resultSet.getString("phone"), resultSet.getInt("age"),
                resultSet.getString("district"), resultSet.getString("street"),
                resultSet.getString("house"), resultSet.getString("flat"),
                resultSet.getString("login"), resultSet.getString("password")
            )
        }
        return userList
    }

    override fun takeUserById(id: Long): Users {
        val user = jdbcTemplate.query("SELECT * FROM users where user_id = " + id) { resultSet: ResultSet, _: Int ->
            Users(
                resultSet.getLong("user_id"), resultSet.getString("name"),
                resultSet.getString("phone"), resultSet.getInt("age"),
                resultSet.getString("district"), resultSet.getString("street"),
                resultSet.getString("house"), resultSet.getString("flat"),
                resultSet.getString("login"), resultSet.getString("password")
            )
        }
        return user.first()
    }

    override fun saveUser(userRegister: UserRegisterDto): Int {
        return jdbcTemplate.update(
            "INSERT INTO users (name, phone, age, district, street, house, flat, login, password) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
            userRegister.name, userRegister.phone, userRegister.age, userRegister.district, userRegister.street,
            userRegister.house, userRegister.flat, userRegister.login, userRegister.password
        )
    }

    override fun updateUser(userInfo: UserInfo): Int {
        return jdbcTemplate.update(
            "UPDATE users SET name = ?, phone = ?, age = ?, district = ?, street = ?, house = ?, flat = ?, " +
                    "login = ?, password = ? WHERE user_id = ?",
            userInfo.name, userInfo.phone, userInfo.age, userInfo.district, userInfo.street,
            userInfo.house, userInfo.flat, userInfo.email, userInfo.password, userInfo.userId
        )
    }

    override fun updateUser(adminUserInfo: AdminUserInfo) {
        jdbcTemplate.update(
            "UPDATE users SET name = ?, phone = ?, age = ?, district = ?, street = ?, house = ?," +
                    "flat = ? WHERE user_id = ?",
            adminUserInfo.name, adminUserInfo.phone, adminUserInfo.age, adminUserInfo.district,
            adminUserInfo.street, adminUserInfo.house, adminUserInfo.flat, adminUserInfo.userId)
    }

    override fun deleteUser(id: Long) {

        jdbcTemplate.update("DELETE FROM users WHERE user_id = " + id)
    }
}