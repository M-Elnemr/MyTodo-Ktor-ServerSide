package com.elnemr.services.tasks.data

import com.elnemr.services.tasks.domain.Task
import com.elnemr.services.users.domain.User
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object Tasks : Table() {
    val taskId = varchar("taskId", 64)
    val userId = varchar("userId", 64)
    val title = varchar("title", 128)
    val description = varchar("description", 256)
    val category = varchar("category", 64)
    val state = bool("state")
    val startDate = datetime("startDate")
    val endDate = datetime("endDate")

    override val primaryKey = PrimaryKey(taskId)
}

// mapper between table and data class

fun resultRowToTasks(row: ResultRow) = Task(
    row[Tasks.taskId],
    row[Tasks.userId],
    row[Tasks.title],
    row[Tasks.description],
    row[Tasks.category],
    row[Tasks.state],
    row[Tasks.startDate].toString(),
    row[Tasks.endDate].toString(),
)

// String to Local Date Time
fun toLocalDateTime(date: String): LocalDateTime = LocalDateTime.parse(date)