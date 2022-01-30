package app.antran.kmm.playground.days.day7

import app.antran.kmm.playground.days.day7.datasource.network.FeedLoader
import app.antran.kmm.playground.days.day7.entity.Feed

class RssReader internal constructor(
    private val feedLoader: FeedLoader,
    private val feedURL: String = "https://blog.jetbrains.com/kotlin/feed/"
) {
    @Throws(Exception::class)
    suspend fun getAllFeeds(): Feed {
        return feedLoader.getFeed(feedURL)
    }

    companion object
}
