//
// Copyright © 2022 An Tran. All rights reserved.
//

import shared
import SwiftUI
import PagerTabStripView

struct Day4ContentView: View {
    let callback = shared.FunctionCallback()
    
    var body: some View {
        VStack {
            PagerTabStripView() {
                IntFunctionView(callback: callback)
                    .pagerTabItem {
                        TitleBarView(title: "Int")
                    }
                DoubleFunctionView(callback: callback)
                    .pagerTabItem {
                        TitleBarView(title: "Double")
                    }
            }
            
            Spacer()
        }
        .navigationTitle("Function Callback")
    }
}

private struct IntFunctionView: View {
    let callback: FunctionCallback
    
    @State var text: String = "0"
    @State var result: Int32 = 2 // (0 + 1) * 2
    
    var body: some View {
        VStack {
            TextField("Start Value", text: $text)
                .keyboardType(.numberPad)
                .textFieldStyle(.roundedBorder)
            
            Button(
                action: {
                    guard let startValue = Int32(text) else {
                        return
                    }
                    // https://kotlinlang.org/docs/native-objc-interop.html#function-types
                    result = callback.intFunction(startValue: startValue) { value in
                        KotlinInt(integerLiteral: value.intValue + 1)
                    }
                },
                label: {
                    Text("Invoke Int Callback Function")
                }
            )
            
            Text("Result = (\(text) + 1) * 2 = \(result)")
                .font(.system(size: 30))
        }
        .padding()
    }
}

private struct DoubleFunctionView: View {
    let callback: FunctionCallback
    
    @State var text: String = "0"
    @State var result: Double = 0.5 // (0 + 1) / 2
    
    var body: some View {
        VStack {
            TextField("Start Value", text: $text)
                .keyboardType(.numberPad)
                .textFieldStyle(.roundedBorder)
            
            Button(
                action: {
                    guard let startValue = Double(text) else {
                        return
                    }
                    // https://kotlinlang.org/docs/native-objc-interop.html#function-types
                    result = callback.doubleFunction(startValue: startValue) { value in
                        KotlinDouble(value: startValue + 1)
                    }
                },
                label: {
                    Text("Invoke Int Callback Function")
                }
            )
            
            Text("Result = (\(text) + 1) * 2 = \(result)")
                .font(.system(size: 30))
        }
        .padding()
    }
}
