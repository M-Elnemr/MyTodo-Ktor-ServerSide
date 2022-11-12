package com.elnemr.services.users.data

import com.elnemr.database.DatabaseFactory.dbQuery
import com.elnemr.services.users.domain.User
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class UsersDAOImpl : UsersDAO {
    override suspend fun insertUser(user: User): Boolean {
        val queryResult = dbQuery {
            val insertStatement = Users.insert { users ->
                users[userId] = user.userId
                users[email] = user.email
                users[password] = user.password
            }
            insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToUser)
//            {row ->
//                resultRowToUser(row)
//            }
        }

        return queryResult != null
    }

    override suspend fun getUserByEmail(email: String): User? {
        return dbQuery {
            Users.select {
                Users.email eq email
            }.map(::resultRowToUser).singleOrNull()
        }
    }
}

val userDAO: UsersDAO = UsersDAOImpl()