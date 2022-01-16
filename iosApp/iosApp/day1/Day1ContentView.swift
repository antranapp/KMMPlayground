//
// Copyright © 2022 An Tran. All rights reserved.
//

import shared
import SwiftUI

struct Day1ContentView: View {
    let greet = Greeting().greeting()

    var body: some View {
        Text(greet)
    }
}

#if DEBUG
struct Day1ContentView_Previews: PreviewProvider {
    static var previews: some View {
        Day1ContentView()
    }
}
#endif
