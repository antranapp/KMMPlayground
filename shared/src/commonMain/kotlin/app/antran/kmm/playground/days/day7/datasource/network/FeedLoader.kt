package app.antran.kmm.playground.days.day7.datasource.network

import app.antran.kmm.playground.days.day7.entity.Feed
import io.ktor.client.*
import io.ktor.client.request.*

internal class FeedLoader(
    private val httpClient: HttpClient,
    private val parser: FeedParser
) {
    suspend fun getFeed(url: String): Feed {
        val xml = httpClient.get<String>(url)
        return parser.parse(url, xml)
    }
}