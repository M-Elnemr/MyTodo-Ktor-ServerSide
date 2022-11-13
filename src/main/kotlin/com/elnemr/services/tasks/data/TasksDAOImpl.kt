package com.elnemr.services.tasks.data

import com.elnemr.database.DatabaseFactory.dbQuery
import com.elnemr.services.tasks.domain.Task
import org.jetbrains.exposed.sql.*

class TasksDAOImpl : TasksDAO {
    override suspend fun insertTask(task: Task): Boolean {
        val queryResult = dbQuery {
            val insertStatement = Tasks.insert { tasks ->
                tasks[taskId] = task.taskId
                tasks[userId] = task.userId
                tasks[title] = task.title
                tasks[description] = task.description
                tasks[category] = task.category
                tasks[state] = task.state
                tasks[startDate] = toLocalDateTime(task.startDate)
                tasks[endDate] = toLocalDateTime(task.endDate)
            }
            insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToTasks)

        }
        return queryResult != null
    }

    override suspend fun deleteTask(taskId: String): Boolean {
        return dbQuery {
            Tasks.deleteIgnoreWhere {
                Tasks.taskId eq taskId
            }
        } > 0
    }

    override suspend fun getTaskById(taskId: String): Task? {
        return dbQuery {
            Tasks.select {
                Tasks.taskId eq taskId
            }.mapNotNull(::resultRowToTasks).singleOrNull()
        }
    }

    override suspend fun getTasksByUserId(userId: String): List<Task> {
        return dbQuery {
            Tasks.select {
                Tasks.userId eq userId
            }.map(::resultRowToTasks)
        }
    }

    override suspend fun updateTaskDescriptionByTaskId(taskId: String, description: String): Boolean {
        return dbQuery {
            Tasks.update({ Tasks.taskId eq taskId }) { tasks ->
                tasks[Tasks.description] = description
            }
        } > 0
    }

    override suspend fun updateTaskStateByTaskId(taskId: String, state: Boolean): Boolean {
        return dbQuery {
            Tasks.update({ Tasks.taskId eq taskId }) { tasks ->
                tasks[Tasks.state] = state
            }
        } > 0
    }

    override suspend fun updateTaskEndDateByTaskId(taskId: String, endDate: String): Boolean {
        return dbQuery {
            Tasks.update ( {Tasks.taskId eq taskId} ){ tasks->
                tasks[Tasks.endDate] = toLocalDateTime(endDate)
            }
        } > 0
    }
}

val tasksDAO: TasksDAO = TasksDAOImpl()