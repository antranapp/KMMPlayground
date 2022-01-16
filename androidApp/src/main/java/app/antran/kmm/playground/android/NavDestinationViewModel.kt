package app.antran.kmm.playground.android

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class NavDestinationViewModel: ViewModel() {
    var title = mutableStateOf("")
        private set

    fun setTitle(newTitle: String) {
        title.value = newTitle
    }
}