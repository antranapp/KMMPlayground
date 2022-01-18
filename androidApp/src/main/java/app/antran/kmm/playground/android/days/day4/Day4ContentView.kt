package app.antran.kmm.playground.android.days.day4

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import app.antran.kmm.playground.android.NavDestinationViewModel
import app.antran.kmm.playground.day4.FunctionCallback

@Composable
fun Day4ContentView(destinationViewModel: NavDestinationViewModel) {
    LaunchedEffect(Unit) {
        destinationViewModel.setTitle("Function Callback")
    }

    val callback = FunctionCallback()
    var tabIndex by remember { mutableStateOf(0) } // 1.
    val tabTitles = listOf("Int", "Double")

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TabRow(selectedTabIndex = tabIndex) { // 3.
            tabTitles.forEachIndexed { index, title ->
                Tab(selected = tabIndex == index, // 4.
                    onClick = { tabIndex = index },
                    text = { Text(text = title) }) // 5.
            }
        }
        when (tabIndex) { // 6.
            0 -> intFunctionView(callback = callback)
            1 -> doubleFunctionView(callback = callback)
        }
    }
}

@Composable
private fun intFunctionView(callback: FunctionCallback) {
    val text = remember { mutableStateOf(TextFieldValue("0")) }
    var result = remember { mutableStateOf((0+1) * 2) }

    TextField(
        value = text.value,
        onValueChange = { text.value = it },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )

    Button(onClick = {
        val startValue = text.value.text.toInt()
        result.value = callback.intFunction(startValue) { startValue + 1 }
    }) {
        Text(text = "Invoke Int Callback Function")
    }

    Text(
        text = "Result = (${text.value.text}  + 1) * 2 = ${result.value}" ,
        fontSize = 30.sp
    )
}

@Composable
private fun doubleFunctionView(callback: FunctionCallback) {
    val text = remember { mutableStateOf(TextFieldValue("0")) }
    var result: MutableState<Double> = remember { mutableStateOf((0.0+1.0) * 2.0) }

    TextField(
        value = text.value,
        onValueChange = { text.value = it },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )

    Button(onClick = {
        val startValue = text.value.text.toDouble()
        result.value = callback.doubleFunction(startValue) { startValue + 1 }
    }) {
        Text(text = "Invoke Double Callback Function")
    }

    Text(
        text = "Result = (${text.value.text}  + 1) * 2 = ${result.value}" ,
        fontSize = 30.sp
    )
}