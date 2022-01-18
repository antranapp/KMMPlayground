package app.antran.kmm.playground.android.days.day1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.antran.kmm.playground.android.NavDestinationViewModel
import app.antran.kmm.playground.day1.Greeting

fun greet(): String {
    return Greeting().greeting()
}

@Composable
fun Day1ContentView(destinationViewModel: NavDestinationViewModel) {
    LaunchedEffect(Unit) {
        destinationViewModel.setTitle("Day 1")
    }

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = greet())
    }
}