package app.antran.kmm.playground.android.days.day6

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Cached
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import app.antran.kmm.playground.android.days.day5.TodoListView
import app.antran.kmm.playground.days.day5.entity.Todo
import app.antran.kmm.playground.days.day6.DatabaseDriverFactory
import app.antran.kmm.playground.days.day6.TodoWithCacheSDK
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@Composable
fun Day6ContentView(onExitClick: () -> Unit) {
    val viewModel: TodosViewModel = viewModel(factory =  TodosViewModelFactory(context = LocalContext.current))

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Day 6: SqlDelight Caching") },
                navigationIcon = {
                    IconButton(onClick = { onExitClick() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.reload(true) }) {
                        Icon(imageVector = Icons.Filled.Cached, contentDescription = null)
                    }
                }
            )
        }
    ) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (val state = viewModel.state.collectAsState().value) {
                TodosViewModel.State.Loading -> {
                    Text("Loading")
                }
                is TodosViewModel.State.Error -> {
                    Text(text = state.error, color = Color.Red)
                }
                is TodosViewModel.State.Data -> {
                    TodoListView(state.todos)
                }
            }
        }
    }
}

// https://stackoverflow.com/questions/67982230/jetpack-compose-pass-parameter-to-viewmodel
internal class TodosViewModelFactory(private val context: Context): ViewModelProvider.NewInstanceFactory() {
    override fun<T: ViewModel?> create(modelClass: Class<T>): T = TodosViewModel(context) as T
}

internal class TodosViewModel(context: Context): ViewModel() {
    sealed class State {
        object Loading: State()
        data class Data(val todos: List<Todo>): State()
        data class Error(val error: String): State()
    }

    private val todoWithCacheSDK = TodoWithCacheSDK(DatabaseDriverFactory(context = context))
    private val _state = MutableStateFlow<State>(State.Loading)
    val state = _state.asStateFlow()

    init {
        reload(false)
    }

    fun reload(force: Boolean) {
        viewModelScope.launch {
            val data = todoWithCacheSDK.getTodos(force)
            _state.value = State.Data(data)
        }
    }
}