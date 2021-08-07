package ktor.docker

import com.fasterxml.jackson.databind.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun main(vararg args: String) {
    // creates a simple ktor server
    createServer().start(wait = true)
}

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
private suspend fun createServer(): NettyApplicationEngine {
    return embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            jackson {
                enable(SerializationFeature.INDENT_OUTPUT)
            }
        }

        routing {
            get("/") {
                withContext(Dispatchers.IO) {
                    call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
                }
            }

            get("/json/jackson") {
                withContext(Dispatchers.IO) {
                    call.respond(mapOf("hello" to "world"))
                }
            }
        }
    }
}
