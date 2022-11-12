package com.elnemr.services.users.data

import com.elnemr.services.users.domain.User

interface UsersDAO {
    suspend fun insertUser(user: User): Boolean
    suspend fun getUserByEmail(email: String): User?
}