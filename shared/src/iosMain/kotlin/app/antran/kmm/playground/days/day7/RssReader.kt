package app.antran.kmm.playground.days.day7

import app.antran.kmm.playground.days.day7.datasource.network.FeedLoader
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

fun RssReader.Companion.create(withLog: Boolean) = RssReader(
    FeedLoader(
        IosHttpClient(withLog),
        IosFeedParser()
    ),
).also {
    if (withLog) Napier.base(DebugAntilog())
}