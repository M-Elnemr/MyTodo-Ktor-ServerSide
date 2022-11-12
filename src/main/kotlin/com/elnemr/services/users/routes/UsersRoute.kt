package com.elnemr.services.users.routes

import com.elnemr.services.users.data.userRepo
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.usersRoute() {
    route("/user") {
        get {
            call.respondText("User route", status = HttpStatusCode.OK)
        }

        post("/register") {
            val params = call.receiveParameters()
            val email = params["email"]?.lowercase()?.trim() ?: return@post call.respondText(
                "Email is null",
                status = HttpStatusCode.BadRequest
            )

            if (userRepo.isEmailExists(email)) return@post call.respondText(
                "Email is already used!",
                status = HttpStatusCode.BadRequest
            )

            val password = params["password"]?.lowercase()?.trim() ?: return@post call.respondText(
                "Password is null",
                status = HttpStatusCode.BadRequest
            )

            if (password.length < 6) return@post call.respondText(
                "Password is less that 6 digit",
                status = HttpStatusCode.BadRequest
            )

            val user = userRepo.insertUser(email, password) ?: return@post call.respondText(
                "Failed to create the user",
                status = HttpStatusCode.BadRequest
            )

            call.respondText("User successfully created", status = HttpStatusCode.InternalServerError)

        }

        post("/login") {
            val params = call.receiveParameters()

            val email = params["email"]?.lowercase()?.trim() ?: return@post call.respondText(
                "Email is null",
                status = HttpStatusCode.BadRequest
            )

            val password = params["password"]?.lowercase()?.trim() ?: return@post call.respondText(
                "Password is null",
                status = HttpStatusCode.BadRequest
            )

            val user = userRepo.getUserByEmail(email) ?: return@post call.respondText(
                "Email is not exist",
                status = HttpStatusCode.BadRequest
            )

            if (!userRepo.isUserPasswordValid(password, user.password)) return@post call.respondText(
                "Incorrect password",
                status = HttpStatusCode.BadRequest
            )

            call.respond(
                message = user.userId,
                status = HttpStatusCode.OK
            )

        }

    }
}