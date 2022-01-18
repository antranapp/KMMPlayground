package app.antran.kmm.playground.android

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.antran.kmm.playground.android.days.day1.Day1ContentView
import app.antran.kmm.playground.android.days.day2.Day2ContentView
import app.antran.kmm.playground.android.days.day3.Day3ContentView
import app.antran.kmm.playground.android.days.day4.Day4ContentView
import app.antran.kmm.playground.android.days.day5.Day5ContentView

@Composable
fun AppView(destinationViewModel: NavDestinationViewModel = viewModel()) {
    val navController = rememberNavController()
    var canPop by remember { mutableStateOf(false) }

    navController.addOnDestinationChangedListener { controller, _, _ ->
        canPop = controller.previousBackStackEntry != null
    }

    val navigationIcon: (@Composable () -> Unit)? =
        if (canPop) {
            {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                }
            }
        } else {
            null
        }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(destinationViewModel.title.value) },
                navigationIcon = navigationIcon
            )
        }
    ) {
        DashboardNavHost(destinationViewModel = destinationViewModel)
    }
}

@Composable
private fun DashboardNavHost(destinationViewModel: NavDestinationViewModel) {
    val navController = rememberNavController()
    NavHost(
        navController,
        startDestination = "dashboard"
    ) {
        composable("dashboard") {
            DashboardView(navController = navController, destinationViewModel = destinationViewModel)
        }

        composable("day1") {
            Day1ContentView(destinationViewModel = destinationViewModel)
        }

        composable("day2") {
            Day2ContentView(destinationViewModel = destinationViewModel)
        }

        composable("day3") {
            Day3ContentView(destinationViewModel = destinationViewModel)
        }

        composable("day4") {
            Day4ContentView(destinationViewModel = destinationViewModel)
        }

        composable("day5") {
            Day5ContentView(destinationViewModel = destinationViewModel)
        }
    }
}
