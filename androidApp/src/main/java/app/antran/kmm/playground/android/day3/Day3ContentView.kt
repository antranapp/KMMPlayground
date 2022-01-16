package app.antran.kmm.playground.android.day3

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import app.antran.kmm.playground.android.NavDestinationViewModel
import app.antran.kmm.playground.day3.Capitalise
import day2.Addition

@Composable
fun Day3ContentView(destinationViewModel: NavDestinationViewModel) {

    LaunchedEffect(Unit) {
        destinationViewModel.setTitle("Day 3")
    }

    val capitalise = Capitalise()
    val text = remember { mutableStateOf(TextFieldValue()) }

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = text.value,
            onValueChange = { text.value = it }
        )

        Text(
            text = "Result = ${capitalise.toUpperCase(text.value.text)}" ,
            fontSize = 30.sp
        )
    }
}