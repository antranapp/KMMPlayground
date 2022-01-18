//
// Copyright © 2022 An Tran. All rights reserved.
//

import shared
import SwiftUI

struct Day2ContentView: View {
    let addition = shared.Addition()
    
    @State var xValue: String = "0"
    @State var yValue: String = "0"
    @State var result: Int = 0
    
    var body: some View {
        VStack {
            TextField("xValue", text: $xValue)
                .keyboardType(.numberPad)
                .textFieldStyle(.roundedBorder)
            TextField("yValue", text: $yValue)
                .keyboardType(.numberPad)
                .textFieldStyle(.roundedBorder)
            
            Button(
                action: {
                    guard let xIntvalue = Int32(xValue),
                          let yIntValue = Int32(yValue)
                    else {
                        return
                    }
                    result = Int(addition.add(x: xIntvalue, y: yIntValue))
                },
                label: {
                    Text("Calculate Adition")
                }
            )
            
            Text("Result = \(result)")
                .font(.system(size: 30))
        }
        .padding()
        .navigationTitle("Addition")
    }
}
