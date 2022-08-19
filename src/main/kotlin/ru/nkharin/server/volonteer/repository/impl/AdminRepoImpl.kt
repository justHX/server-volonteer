package ru.nkharin.server.volonteer.repository.impl

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.nkharin.server.volonteer.entity.Admins
import ru.nkharin.server.volonteer.repository.AdminRepo
import java.sql.ResultSet

@Repository
class AdminRepoImpl constructor(val jdbcTemplate: JdbcTemplate) : AdminRepo {

    override fun takeAdminList(login: String, password: String) : List<Admins> {
       val adminList = jdbcTemplate.query("SELECT * FROM admins") { resultSet: ResultSet, _: Int ->
           Admins(
               resultSet.getLong("admin_id"), resultSet.getString("login"),
               resultSet.getString("password"), resultSet.getString("full_name")
           )
       }
        return adminList
    }
}
