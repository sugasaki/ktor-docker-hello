package docker.ktor.plugins

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.configureRouting() {

    routing {
        get("/") {
            call.respondText("Hello World!!!!!!")
        }

        get("/news") {
            call.respondText("news")
        }
    }
}
