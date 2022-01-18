//
// Copyright © 2022 An Tran. All rights reserved.
//

import shared
import SwiftUI

struct Day3ContentView: View {
    let capitalise = shared.Capitalise()
    
    @State var text: String = ""
    
    var body: some View {
        VStack {
            TextField("Text", text: $text)
                .textFieldStyle(.roundedBorder)
           
            Text("Result = \(capitalise.toUpperCase(input: text))")
                .font(.system(size: 30))
        }
        .padding()
        .navigationTitle("Capitalise")
    }
}
