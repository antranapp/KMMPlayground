package app.antran.kmm.playground.android.days.day8

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import app.antran.kmm.playground.android.days.day8.ui.FeedList
import app.antran.kmm.playground.days.day8.create
import app.antran.kmm.playground.days.day8.datasource.storage.RssReader2
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow

class Day8Activity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigator(screen = Day8Screen(this) {
                this.finish()
            })
        }
    }
}

internal class Day8Screen(
    private val context: Context,
    private val onExitClick: () -> Unit
): Screen {
    @Composable
    override fun Content() {
        val store = FeedStore(RssReader2.create(context))
        val navigator = LocalNavigator.currentOrThrow
        Day8ContentView(
            store = store,
            onExitClick = onExitClick,
            onPostClick = { post ->
                post.link?.let { url ->
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    context.startActivity(intent)
                }
            },
            onEditFeedClick = {
                navigator.push(FeedListScreen(store = store))
            }
        )
    }
}

internal class FeedListScreen(
    private val store: FeedStore
): Screen {
    @Composable
    override fun Content() {
        FeedList(store = store)
    }
}