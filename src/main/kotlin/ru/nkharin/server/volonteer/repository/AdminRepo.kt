package ru.nkharin.server.volonteer.repository

import ru.nkharin.server.volonteer.entity.Admins

interface AdminRepo {
    fun takeAdminList(login: String, password: String): List<Admins>
}