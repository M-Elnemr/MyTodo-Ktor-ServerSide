package com.elnemr.services.tasks.data

import com.elnemr.services.tasks.domain.Task
import com.elnemr.services.tasks.domain.TasksRepository
import java.time.LocalDateTime
import java.util.*

class TasksRepositoryImpl : TasksRepository {
    override suspend fun insertTask(userId: String, title: String, description: String, category: String): Task? {
        val newTask = Task(
            UUID.randomUUID().toString(),
            userId,
            title,
            description,
            category,
            false,
            LocalDateTime.now().toString(),
            LocalDateTime.now().toString()
        )
        return if (tasksDAO.insertTask(newTask)) newTask else null
    }

    override suspend fun deleteTask(taskId: String): Boolean =
        tasksDAO.deleteTask(taskId)

    override suspend fun getTaskById(taskId: String): Task? =
        tasksDAO.getTaskById(taskId)

    override suspend fun getTasksByUserId(userId: String): List<Task> =
        tasksDAO.getTasksByUserId(userId)

    override suspend fun updateTaskDescriptionByTaskId(taskId: String, description: String): Task? =
        if (tasksDAO.updateTaskDescriptionByTaskId(taskId, description)) tasksDAO.getTaskById(taskId) else null


    override suspend fun updateTaskStateByTaskId(taskId: String, state: Boolean): Task? =
        if (tasksDAO.updateTaskStateByTaskId(taskId, state)) tasksDAO.getTaskById(taskId) else null


    override suspend fun updateTaskEndDateByTaskId(taskId: String, endDate: String): Task? =
        if (tasksDAO.updateTaskEndDateByTaskId(taskId, endDate)) tasksDAO.getTaskById(taskId) else null
}

val taskRepo: TasksRepository = TasksRepositoryImpl()