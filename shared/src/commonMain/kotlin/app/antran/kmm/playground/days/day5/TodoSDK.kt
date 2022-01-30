package app.antran.kmm.playground.days.day5

import app.antran.kmm.playground.days.day5.entity.Todo
import app.antran.kmm.playground.days.day5.network.TodoApi

class TodoSDK {
    private val api = TodoApi()

    @Throws(Exception::class) suspend fun getTodos(): List<Todo> {
        return api.getAllTodos()
    }
}