package com.elnemr.services.tasks.data

import com.elnemr.services.tasks.domain.Task

interface TasksDAO {
    suspend fun insertTask(task: Task): Boolean
    suspend fun deleteTask(taskId: String): Boolean
    suspend fun getTaskById(taskId: String): Task?
    suspend fun getTasksByUserId(userId: String): List<Task>
    suspend fun updateTaskDescriptionByTaskId(taskId: String, description: String): Boolean
    suspend fun updateTaskStateByTaskId(taskId: String, state: Boolean): Boolean
    suspend fun updateTaskEndDateByTaskId(taskId: String, endDate: String): Boolean
}