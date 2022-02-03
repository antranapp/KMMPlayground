package app.antran.kmm.playground.days.day5.network

import app.antran.kmm.playground.days.day5.entity.Todo
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*

class TodoApi {
    private val httpClient = HttpClient {
        install(JsonFeature) {
            val json = kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            }
            serializer = KotlinxSerializer(json)
        }
    }

    suspend fun getAllTodos(): List<Todo> {
        return httpClient.get(SERVICE_ENDPOINT)
    }

    companion object {
        private const val SERVICE_ENDPOINT = "https://jsonplaceholder.typicode.com/todos"
    }
}