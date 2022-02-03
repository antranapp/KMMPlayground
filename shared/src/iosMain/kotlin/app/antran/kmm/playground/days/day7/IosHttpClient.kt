package app.antran.kmm.playground.days.day7

import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.engine.ios.*
import io.ktor.client.features.logging.*

// https://ktor.io/docs/http-client-engines.html#ios
internal fun IosHttpClient(withLog: Boolean) = HttpClient(Ios) {
    engine {
        configureRequest {
            setAllowsCellularAccess(true)
        }
    }
    if (withLog) install(Logging) {
        level = LogLevel.INFO
        logger = object : Logger {
            override fun log(message: String) {
                Napier.v(tag = "IosHttpClient", message = message)
            }
        }
    }
}