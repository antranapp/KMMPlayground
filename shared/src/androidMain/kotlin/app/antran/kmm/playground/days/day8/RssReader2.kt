package app.antran.kmm.playground.days.day8

import android.content.Context
import app.antran.kmm.playground.days.day7.AndroidFeedParser
import app.antran.kmm.playground.days.day7.AndroidHttpClient
import app.antran.kmm.playground.days.day7.datasource.network.FeedLoader
import app.antran.kmm.playground.days.day8.datasource.storage.FeedStorage
import app.antran.kmm.playground.days.day8.datasource.storage.RssReader2
import com.russhwolf.settings.AndroidSettings
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import kotlinx.serialization.json.Json

// Creating a singleton-like RssReader
fun RssReader2.Companion.create(ctx: Context, withLog: Boolean = false) = RssReader2(
    FeedLoader(
        AndroidHttpClient(withLog),
        AndroidFeedParser()
    ),
    FeedStorage(
        AndroidSettings(ctx.getSharedPreferences("rss_reader_pref", Context.MODE_PRIVATE)),
        Json {
            useAlternativeNames = false
            ignoreUnknownKeys = true
            isLenient = true
            encodeDefaults = false
        }
    )
).also {
    if (withLog) Napier.base(DebugAntilog())
}