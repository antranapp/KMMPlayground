package app.antran.kmm.playground.android.days.day7

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class Day7Activity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // onExitClick: https://stackoverflow.com/a/67402808
            Day7ContentView(
                onExitClick = { this.finish() },
                onPostClick = { post ->
                    post.link?.let { url ->
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        this.startActivity(intent)
                    }
                }
            )
        }
    }
}
