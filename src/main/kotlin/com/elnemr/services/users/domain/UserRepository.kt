package com.elnemr.services.users.domain

interface UserRepository {
    suspend fun insertUser(email: String, password: String): User?
    suspend fun getUserByEmail(email: String): User?
}