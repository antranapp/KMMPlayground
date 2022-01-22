package app.antran.kmm.playground.day5.network

import app.antran.kmm.playground.day5.entity.Todo
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*

class TodoApi {
    private val httpClient = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    suspend fun getAllTodos(): List<Todo> {
        return httpClient.get(SERVICE_ENDPOINT)
    }

    companion object {
        private const val SERVICE_ENDPOINT = "https://jsonplaceholder.typicode.com/todos"
    }
}