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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun DashboardView(
    navController: NavHostController,
    destinationViewModel: NavDestinationViewModel
) {
    LaunchedEffect(Unit) {
        destinationViewModel.setTitle("KMM Playground")
    }

    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(items = days, itemContent = { day ->
            DashboardRow(
                navController = navController,
                day = day
            )

            Divider()
        })
    }
}

@Composable
private fun DashboardRow(
    navController: NavHostController,
    day: Day
) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable { navController.navigate(day.route) }
    ) {
        Text(
            text = day.title,
            fontSize = 18.sp
        )
    }
}