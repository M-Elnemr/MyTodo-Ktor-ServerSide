package com.elnemr.services.tasks.data

import com.elnemr.services.tasks.domain.Task
import com.elnemr.services.tasks.domain.TasksRepository

class TasksRepositoryImpl : TasksRepository {
    override suspend fun insertTask(task: Task): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTask(taskId: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getTaskById(taskId: String): Task? {
        TODO("Not yet implemented")
    }

    override suspend fun getTasksByUserId(userId: String): List<Task> {
        TODO("Not yet implemented")
    }

    override suspend fun updateTaskDescriptionByTaskId(taskId: String, description: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun updateTaskStateByTaskId(taskId: String, state: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun updateTaskEndDateByTaskId(taskId: String, endDate: String): Boolean {
        TODO("Not yet implemented")
    }

}