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
