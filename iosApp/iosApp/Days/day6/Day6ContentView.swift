//
// Copyright © 2022 An Tran. All rights reserved.
//

import Foundation
import shared
import SwiftUI

struct Day6ContentView: View {
    @StateObject private var viewModel = TodosViewModel()
    
    var body: some View {
        VStack {
            switch viewModel.state {
            case .loading:
                Text("Loading ...")
                    .onAppear {
                        viewModel.load()
                    }
            case .error:
                Text("Error!")
            case .loaded(let todos):
                List(todos, id: \.self) {
                    TodoRowView(todo: $0)
                }
            }
        }
        .toolbar {
            ToolbarItem(placement: .navigationBarTrailing) {
                Button(
                    action: {
                        viewModel.load()
                    },
                    label: {
                        Image(systemName: "circle")
                    }
                )
            }
        }
        .navigationTitle("SqlDelight Caching")
    }
}
