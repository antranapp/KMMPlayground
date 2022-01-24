package app.antran.kmm.playground.days.day6

import app.antran.kmm.playground.days.day5.entity.Todo
import app.antran.kmm.playground.days.day5.network.TodoApi
import app.antran.kmm.playground.days.day6.cache.Database

class TodoWithCacheSDK (databaseDriverFactory: DatabaseDriverFactory) {
    private val database = Database(databaseDriverFactory)
    private val api = TodoApi()

    @Throws(Exception::class) suspend fun getTodos(forceReload: Boolean): List<Todo> {
        val cachedLaunches = database.getAllTodos()
        return if (cachedLaunches.isNotEmpty() && !forceReload) {
            // Logger.i {"Loaded from Cache"}
            cachedLaunches
        } else {
            api.getAllTodos().also {
                // Logger.i {"Loaded from Network and saved to cache"}
                database.clearDatabase()
                database.createTodos(it)
            }
        }
    }
}