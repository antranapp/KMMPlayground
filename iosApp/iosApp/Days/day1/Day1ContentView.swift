//
// Copyright © 2022 An Tran. All rights reserved.
//

import shared
import SwiftUI

struct Day1ContentView: View {
    let greet = Greeting().greeting()

    var body: some View {
        Text(greet)
            .navigationTitle("Greeting")
    }
}
