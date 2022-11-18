package com.elnemr.services.users.domain

interface UserRepository {
    suspend fun insertUser(email: String, password: String): User?
    suspend fun getUserByEmail(email: String): User?
    suspend fun isEmailExists(email: String): Boolean
    suspend fun isUserIdExists(userId: String): Boolean
    suspend fun isUserPasswordValid(password: String, hashedPassword: String): Boolean
}