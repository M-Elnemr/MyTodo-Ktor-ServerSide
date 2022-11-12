package com.elnemr.services.users.data

import com.elnemr.services.users.domain.User
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object Users : Table() {
    val userId = varchar("userId", 64)
    val email = varchar("email", 128)
    val password = varchar("password", 128)

    override val primaryKey = PrimaryKey(userId)
}

// mapper between table and data class
fun resultRowToUser(row: ResultRow) = User(
    row[Users.userId],
    row[Users.email],
    row[Users.password]
)