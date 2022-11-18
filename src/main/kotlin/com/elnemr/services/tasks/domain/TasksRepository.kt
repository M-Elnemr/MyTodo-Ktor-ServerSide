package com.elnemr.services.tasks.domain

interface TasksRepository {
    suspend fun insertTask(userId: String, title: String, description: String, category: String): Task?
    suspend fun deleteTask(taskId: String): Boolean
    suspend fun getTaskById(taskId: String): Task?
    suspend fun getTasksByUserId(userId: String): List<Task>
    suspend fun updateTaskDescriptionByTaskId(taskId: String, description: String): Task?
    suspend fun updateTaskStateByTaskId(taskId: String, state: Boolean): Task?
    suspend fun updateTaskEndDateByTaskId(taskId: String, endDate: String): Task?
}