package app.antran.kmm.playground.android

data class Day(val title: String, val route: String){}

val days = listOf(
    Day(title = "Day 1: Greeting", route = "day1"),
    Day(title = "Day 2: Addition", route = "day2"),
    Day(title = "Day 3: Capitalise", route = "day3"),
    Day(title = "Day 4: Function Callback", route = "day4"),
    Day(title = "Day 5: Ktor Network", route = "day5")
)