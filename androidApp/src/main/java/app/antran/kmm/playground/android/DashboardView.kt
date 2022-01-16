package app.antran.kmm.playground.android

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun DashboardView(
    navController: NavHostController,
    destinationViewModel: NavDestinationViewModel
) {
    LaunchedEffect(Unit) {
        destinationViewModel.setTitle("KMM Playground")
    }

    val list = listOf(
        "day1", "day2", "day3"
    )
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(items = list, itemContent = { item ->
            when(item) {
                "day1" -> {
                    DashboardRow(
                        navController = navController,
                        title = "Day 1: Greeting",
                        route = "day1"
                    )
                }
                "day2" -> {
                    DashboardRow(
                        navController = navController,
                        title = "Day 2: Addition",
                        route = "day2"
                    )
                }
                "day3" -> {
                    DashboardRow(
                        navController = navController,
                        title = "Day 3: Capitalise",
                        route = "day3"
                    )
                }                else -> {
                    Text("invalid destination")
                }
            }
            Divider()
        })
    }
}

@Composable
private fun DashboardRow(
    navController: NavHostController,
    title: String,
    route: String
) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable { navController.navigate(route) }
    ) {
        Text(text = title)
    }
}