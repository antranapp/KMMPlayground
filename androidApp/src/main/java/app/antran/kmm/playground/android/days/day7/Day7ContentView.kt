package app.antran.kmm.playground.android.days.day7

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
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
import app.antran.kmm.playground.days.day7.RssReader
import app.antran.kmm.playground.days.day7.create
import app.antran.kmm.playground.days.day7.entity.Feed
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@Composable
fun Day7ContentView(onExitClick: () -> Unit) {
    val viewModel: RssReaderViewModel = viewModel(factory =  RssReaderViewModelFactory(context = LocalContext.current))

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Day 7: RSS Reader") },
                navigationIcon = {
                    IconButton(onClick = { onExitClick() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.reload() }) {
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
                RssReaderViewModel.State.Loading -> {
                    Text("Loading")
                }
                is RssReaderViewModel.State.Error -> {
                    Text(text = state.error, color = Color.Red)
                }
                is RssReaderViewModel.State.Data -> {
                    val listState = rememberLazyListState()
                    PostsView(
                        modifier = Modifier.weight(1f),
                        posts = state.feed.posts,
                        listState = listState
                    ) {  }
                }
            }
        }
    }
}

// https://stackoverflow.com/questions/67982230/jetpack-compose-pass-parameter-to-viewmodel
internal class RssReaderViewModelFactory(private val context: Context): ViewModelProvider.NewInstanceFactory() {
    override fun<T: ViewModel?> create(modelClass: Class<T>): T = RssReaderViewModel(context) as T
}

internal class RssReaderViewModel(context: Context): ViewModel() {
    sealed class State {
        object Loading: State()
        data class Data(val feed: Feed): State()
        data class Error(val error: String): State()
    }

    private val rssReader = RssReader.create(context)
    private var _state = MutableStateFlow<State>(State.Loading)
    val state = _state.asStateFlow()

    init {
        reload()
    }

    fun reload() {
        viewModelScope.launch {
            val data = rssReader.getAllFeeds()
            _state.value = State.Data(data)
        }
    }
}