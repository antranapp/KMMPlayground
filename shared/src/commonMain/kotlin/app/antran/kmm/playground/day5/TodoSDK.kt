package app.antran.kmm.playground.day5

import app.antran.kmm.playground.day5.entity.Todo
import app.antran.kmm.playground.day5.network.TodoApi

class TodoSDK {
    private val api = TodoApi()

    @Throws(Exception::class) suspend fun getTodos(): List<Todo> {
        return api.getAllTodos()
    }
}