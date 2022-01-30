package app.antran.kmm.playground.android.days.day5

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import app.antran.kmm.playground.android.NavDestinationViewModel
import app.antran.kmm.playground.days.day5.TodoSDK
import app.antran.kmm.playground.days.day5.entity.Todo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@Composable
fun Day5ContentView(destinationViewModel: NavDestinationViewModel) {
    LaunchedEffect(Unit) {
        destinationViewModel.setTitle("Ktor Networking")
    }

    val viewModel = viewModel<TodosViewModel>()

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when(val state = viewModel.state.collectAsState().value) {
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

@Composable
fun TodoListView(todos: List<Todo>) {
    LazyColumn {
        items(todos) { todo ->
            TodoRowView(todo)
            Divider()
        }
    }
}

@Composable
fun TodoRowView(todo: Todo) {
    val icon = if (todo.completed) Icons.Filled.CheckCircle else Icons.Outlined.Circle
    Row(
        modifier = Modifier.fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp)
    ) {
        Icon(
            icon,
            contentDescription = "todo",
            modifier = Modifier.padding(end = 16.dp)
        )
        Text(
            text = todo.title,
            fontSize = 18.sp
        )
    }
}

internal class TodosViewModel: ViewModel() {
    sealed class State {
        object Loading: State()
        data class Data(val todos: List<Todo>): State()
        data class Error(val error: String): State()
    }

    private var todoSDK = TodoSDK()
    private var _state = MutableStateFlow<State>(State.Loading)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val data = todoSDK.getTodos()
            _state.value = State.Data(data)
        }
    }
}