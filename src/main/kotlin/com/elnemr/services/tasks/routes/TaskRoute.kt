package com.elnemr.services.tasks.routes

import com.elnemr.services.tasks.data.taskRepo
import com.elnemr.services.users.data.userRepo
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.taskRoute() {
    route("/task") {
        get {
            call.respondText("tesk", status = HttpStatusCode.OK)
        }

        get(path = "{userId}") {
            val userId = call.parameters["userId"] ?: return@get call.respondText(
                "userId is null",
                status = HttpStatusCode.BadRequest
            )
            call.respond(message = taskRepo.getTasksByUserId(userId), status = HttpStatusCode.OK)

        }

        post {
            val taskFormParam = call.receiveParameters()

            val userId = taskFormParam["userId"] ?: return@post call.respondText(
                "UserId is null",
                status = HttpStatusCode.BadRequest
            )

            if (!userRepo.isUserIdExists(userId)) return@post call.respondText(
                "user is not exist",
                status = HttpStatusCode.NotAcceptable
            )

            val title = taskFormParam["title"] ?: return@post call.respondText(
                "Title is null",
                status = HttpStatusCode.BadRequest
            )

            val description = taskFormParam["description"] ?: return@post call.respondText(
                "Description is null",
                status = HttpStatusCode.BadRequest
            )

            val category = taskFormParam["category"] ?: return@post call.respondText(
                "Category is null",
                status = HttpStatusCode.BadRequest
            )

            val newTask = taskRepo.insertTask(userId, title, description, category) ?: return@post call.respondText(
                "Failed",
                status = HttpStatusCode.InternalServerError
            )

            call.respond(message = newTask, status = HttpStatusCode.Created)

        }

        patch("/description") {
            val taskFormParam = call.receiveParameters()

            val taskId = taskFormParam["taskId"] ?: return@patch call.respondText(
                "taskId is null",
                status = HttpStatusCode.BadRequest
            )

            if (taskRepo.getTaskById(taskId) == null) return@patch call.respondText(
                "task is not exist",
                status = HttpStatusCode.NotAcceptable
            )

            val description = taskFormParam["description"] ?: return@patch call.respondText(
                "Description is null",
                status = HttpStatusCode.BadRequest
            )

            val newTask = taskRepo.updateTaskDescriptionByTaskId(taskId, description) ?: return@patch call.respondText(
                "Failed",
                status = HttpStatusCode.InternalServerError
            )

            call.respond(message = newTask, status = HttpStatusCode.OK)
        }

        patch("/state") {
            val taskFormParam = call.receiveParameters()

            val taskId = taskFormParam["taskId"] ?: return@patch call.respondText(
                "taskId is null",
                status = HttpStatusCode.BadRequest
            )

            if (taskRepo.getTaskById(taskId) == null) return@patch call.respondText(
                "task is not exist",
                status = HttpStatusCode.NotAcceptable
            )

            val state = taskFormParam["state"] ?: return@patch call.respondText(
                "state is null",
                status = HttpStatusCode.BadRequest
            )

            val newTask = taskRepo.updateTaskStateByTaskId(taskId, state.toBoolean()) ?: return@patch call.respondText(
                "Failed",
                status = HttpStatusCode.InternalServerError
            )

            call.respond(message = newTask, status = HttpStatusCode.OK)
        }

        patch("/completion") {
            val taskFormParam = call.receiveParameters()

            val taskId = taskFormParam["taskId"] ?: return@patch call.respondText(
                "taskId is null",
                status = HttpStatusCode.BadRequest
            )

            if (taskRepo.getTaskById(taskId) == null) return@patch call.respondText(
                "task is not exist",
                status = HttpStatusCode.NotAcceptable
            )

            val endDate = taskFormParam["endDate"] ?: return@patch call.respondText(
                "endDate is null",
                status = HttpStatusCode.BadRequest
            )

            val newTask = taskRepo.updateTaskEndDateByTaskId(taskId, endDate) ?: return@patch call.respondText(
                "Failed",
                status = HttpStatusCode.InternalServerError
            )

            call.respond(message = newTask, status = HttpStatusCode.OK)
        }

        delete {
            val taskFormParam = call.receiveParameters()

            val taskId = taskFormParam["taskId"] ?: return@delete call.respondText(
                "taskId is null",
                status = HttpStatusCode.BadRequest
            )

            if (taskRepo.getTaskById(taskId) == null) return@delete call.respondText(
                "task is not exist",
                status = HttpStatusCode.NotAcceptable
            )

            val task = taskRepo.deleteTask(taskId)

            if (!task) return@delete call.respondText(
                "Failed",
                status = HttpStatusCode.InternalServerError
            )

            call.respond(message = "Task deleted successfully", status = HttpStatusCode.OK)
        }
    }
}