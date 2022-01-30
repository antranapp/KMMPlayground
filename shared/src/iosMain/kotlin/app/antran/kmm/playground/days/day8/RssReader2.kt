package app.antran.kmm.playground.days.day8

import android.content.Context
import app.antran.kmm.playground.days.day7.IosFeedParser
import app.antran.kmm.playground.days.day7.IosHttpClient
import app.antran.kmm.playground.days.day7.datasource.network.FeedLoader
import app.antran.kmm.playground.days.day8.datasource.storage.FeedStorage
import app.antran.kmm.playground.days.day8.datasource.storage.RssReader2
import com.russhwolf.settings.AppleSettings
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import platform.Foundation.NSUserDefaults
import kotlinx.serialization.json.Json

// Creating a singleton-like RssReader
fun RssReader2.Companion.create(ctx: Context, withLog: Boolean = false) = RssReader2(
    FeedLoader(
        IosHttpClient(withLog),
        IosFeedParser()
    ),
    FeedStorage(
        AppleSettings(NSUserDefaults.standardUserDefaults()),
        Json {
            ignoreUnknownKeys = true
            isLenient = true
            encodeDefaults = false
        }
    )
).also {
    if (withLog) Napier.base(DebugAntilog())
}