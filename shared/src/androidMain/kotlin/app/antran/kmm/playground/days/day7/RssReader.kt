package app.antran.kmm.playground.days.day7

import android.content.Context
import app.antran.kmm.playground.days.day7.datasource.network.FeedLoader
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

// Creating a singleton-like RssReader
fun RssReader.Companion.create(ctx: Context, withLog: Boolean = false) = RssReader(
    FeedLoader(
        AndroidHttpClient(withLog),
        AndroidFeedParser()
    )
).also {
    if (withLog) Napier.base(DebugAntilog())
}