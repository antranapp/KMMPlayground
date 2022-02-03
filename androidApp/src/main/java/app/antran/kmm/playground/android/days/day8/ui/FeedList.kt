package app.antran.kmm.playground.android.days.day8.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.antran.kmm.playground.android.days.day8.FeedAction
import app.antran.kmm.playground.android.days.day8.FeedStore
import app.antran.kmm.playground.android.utils.LocalImage
import app.antran.kmm.playground.days.day7.entity.Feed
import cafe.adriel.voyager.core.screen.Screen
import com.google.accompanist.insets.navigationBarsWithImePadding

@Composable
fun FeedList(store: FeedStore) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        val state = store.observeState().collectAsState()
        val showAddDialog = remember { mutableStateOf(false) }
        val feedForDelete = remember<MutableState<Feed?>> { mutableStateOf(null) }
        FeedItemList(feeds = state.value.feeds) {
            feedForDelete.value = it
        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .navigationBarsWithImePadding(),
            onClick = { showAddDialog.value = true }
        ) {
            LocalImage(
                modifier = Modifier.align(Alignment.Center),
                resName = "ic_add.xml"
            )
        }
        if (showAddDialog.value) {
            AddFeedDialog(
                onAdd = {
                    store.dispatch(FeedAction.Add(it))
                    showAddDialog.value = false
                },
                onDismiss = {
                    showAddDialog.value = false
                }
            )
        }
        feedForDelete.value?.let { feed ->
            DeleteFeedDialog(
                feed = feed,
                onDelete = {
                    store.dispatch(FeedAction.Delete(feed.sourceUrl))
                    feedForDelete.value = null
                },
                onDismiss = {
                    feedForDelete.value = null
                }
            )
        }
    }
}

@Composable
fun FeedItemList(
    feeds: List<Feed>,
    onClick: (Feed) -> Unit
) {
    LazyColumn {
        itemsIndexed(feeds) { i, feed ->
            FeedItem(feed) { onClick(feed) }
        }
    }
}

@Composable
fun FeedItem(
    feed: Feed,
    onClick: () -> Unit
) {
    Row(
        Modifier
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        FeedIcon(feed = feed)
        Spacer(modifier = Modifier.size(16.dp))
        Column {
            Text(
                style = MaterialTheme.typography.body1,
                text = feed.title
            )
            Text(
                style = MaterialTheme.typography.body2,
                text = feed.desc
            )
        }
    }
}
