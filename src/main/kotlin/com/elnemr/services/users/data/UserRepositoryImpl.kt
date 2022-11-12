package com.elnemr.services.users.data

import com.elnemr.services.users.domain.User
import com.elnemr.services.users.domain.UserRepository
import com.elnemr.services.users.domain.toHashPassword
import java.util.*

class UserRepositoryImpl : UserRepository {
    override suspend fun insertUser(email: String, password: String): User? {
        val newUser = User(UUID.randomUUID().toString(), email, toHashPassword(password))
        return if (userDAO.insertUser(newUser)) newUser else null
    }

    override suspend fun getUserByEmail(email: String): User? =
        userDAO.getUserByEmail(email)
}

val userRepo: UserRepository = UserRepositoryImpl()