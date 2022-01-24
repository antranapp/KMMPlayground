package app.antran.kmm.playground.android.days.day7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class Day7Activity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // onExitClick: https://stackoverflow.com/a/67402808
            Day7ContentView() {
                this.finish()
            }
        }
    }
}
