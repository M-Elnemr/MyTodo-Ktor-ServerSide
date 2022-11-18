package com.elnemr.services.users.data

import com.elnemr.services.users.domain.User
import com.elnemr.services.users.domain.UserRepository
import com.elnemr.services.users.domain.toHashPassword
import com.elnemr.services.users.domain.toVerifyHashedPassword
import java.util.*

class UserRepositoryImpl : UserRepository {
    override suspend fun insertUser(email: String, password: String): User? {
        val newUser = User(UUID.randomUUID().toString(), email, toHashPassword(password))
        return if (userDAO.insertUser(newUser)) newUser else null
    }

    override suspend fun getUserByEmail(email: String): User? =
        userDAO.getUserByEmail(email)

    override suspend fun isEmailExists(email: String): Boolean =
        userDAO.getUserByEmail(email) != null

    override suspend fun isUserIdExists(userID: String): Boolean =
        userDAO.getUserById(userID) != null

    override suspend fun isUserPasswordValid(password: String, hashedPassword: String): Boolean =
        toVerifyHashedPassword(password, hashedPassword)

}

val userRepo: UserRepository = UserRepositoryImpl()