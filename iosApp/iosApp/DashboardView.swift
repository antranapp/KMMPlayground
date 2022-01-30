//
// Copyright © 2022 An Tran. All rights reserved.
//

import shared
import SwiftUI

struct DashboardView: View {
    let greet = Greeting().greeting()

    var body: some View {
        NavigationView {
            List {
                NavigationLink("Day 1: Greeting", destination: Day1ContentView())
                NavigationLink("Day 2: Addition", destination: Day2ContentView())
                NavigationLink("Day 3: Capitalise", destination: Day3ContentView())
                NavigationLink("Day 4: Function Callback", destination: Day4ContentView())
                NavigationLink("Day 5: Ktor Network", destination: Day5ContentView())
                NavigationLink("Day 6: SqlDelight Caching", destination: Day6ContentView())
                NavigationLink("Day 7: RssReader", destination: Day7ContentView())
            }
            .navigationTitle("KMM Playground")
        }
    }
}

struct DashboardView_Previews: PreviewProvider {
    static var previews: some View {
        DashboardView()
    }
}
