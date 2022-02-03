package app.antran.kmm.playground.android.days.day8

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.antran.kmm.playground.android.days.day7.PostsView
import app.antran.kmm.playground.android.days.day8.ui.EditIcon
import app.antran.kmm.playground.android.days.day8.ui.FeedIcon
import app.antran.kmm.playground.android.days.day8.ui.FeedList
import app.antran.kmm.playground.days.day7.entity.Feed
import app.antran.kmm.playground.days.day7.entity.Post
import com.google.accompanist.insets.navigationBarsHeight
import kotlinx.coroutines.launch

@Composable
fun Day8ContentView(
    store: FeedStore,
    onExitClick: () -> Unit,
    onPostClick: (Post) -> Unit,
    onEditFeedClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        store.dispatch(FeedAction.Refresh(false))
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Day 8: Multi-sources RSS Reader") },
                navigationIcon = {
                    IconButton(onClick = { onExitClick() }) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                }
            )
        }
    ) {
        val state = store.observeState().collectAsState()
        val posts = remember(state.value.feeds, state.value.selectedFeed) {
            (state.value.selectedFeed?.posts ?: state.value.feeds.flatMap { it.posts })
                .sortedByDescending { it.date }
        }
        Column {
            val coroutineScope = rememberCoroutineScope()
            val listState = rememberLazyListState()
            PostsView(
                modifier = Modifier.weight(1f),
                posts = posts,
                listState = listState
            ) { post -> onPostClick(post) }
            MainFeedBottomBar(
                feeds = state.value.feeds,
                selectedFeed = state.value.selectedFeed,
                onFeedClick = { feed ->
                    coroutineScope.launch { listState.scrollToItem(0) }
                    store.dispatch(FeedAction.SelectFeed(feed))
                },
                onEditClick = { onEditFeedClick() }
            )
            Spacer(
                Modifier
                    .navigationBarsHeight()
                    .fillMaxWidth()
            )
        }
    }
}

private sealed class FeedIcons {
    object All : FeedIcons()
    class FeedIcon(val feed: Feed) : FeedIcons()
    object Edit : FeedIcons()
}

@OptIn(ExperimentalStdlibApi::class)
@Composable
fun MainFeedBottomBar(
    feeds: List<Feed>,
    selectedFeed: Feed?,
    onFeedClick: (Feed?) -> Unit,
    onEditClick: () -> Unit
) {
    val items = buildList {
        add(FeedIcons.All)
        addAll(feeds.map { FeedIcons.FeedIcon(it) })
        add(FeedIcons.Edit)
    }
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ) {
        this.items(items) { item ->
            when (item) {
                is FeedIcons.All -> FeedIcon(
                    feed = null,
                    isSelected = selectedFeed == null,
                    onClick = { onFeedClick(null) }
                )
                is FeedIcons.FeedIcon -> FeedIcon(
                    feed = item.feed,
                    isSelected = selectedFeed == item.feed,
                    onClick = { onFeedClick(item.feed) }
                )
                is FeedIcons.Edit -> EditIcon(onClick = onEditClick)
            }
            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}