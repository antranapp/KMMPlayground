package app.antran.kmm.playground.day6.cache

import app.antran.kmm.playground.day5.entity.Todo
import app.antran.kmm.playground.day6.DatabaseDriverFactory
import app.antran.kmm.playground.shared.day6.cache.AppDatabase

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries

    internal fun clearDatabase() {
        dbQuery.transaction {
            dbQuery.removeAllTodos()
        }
    }

    internal fun createTodos(todos: List<Todo>) {
        dbQuery.transaction {
            todos.forEach {
                insertTodo(it)
            }
        }
    }

    private fun insertTodo(todo: Todo) {
        dbQuery.insertTodo(
            id = todo.id,
            userId = todo.userId,
            title = todo.title,
            completed = todo.completed
        )
    }

    internal fun getAllTodos(): List<Todo> {
        return dbQuery.selectAllTodos(::mapTodoSelecting).executeAsList()
    }

    private fun mapTodoSelecting(
        id: Int,
        userId: Int,
        title: String,
        completed: Boolean,
    ): Todo {
        return Todo(
            id = id,
            userId = userId,
            title = title,
            completed = completed
        )
    }
}