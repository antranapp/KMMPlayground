//
// Copyright © 2022 An Tran. All rights reserved.
//

import shared
import SwiftUI

struct Day5ContentView: View {
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
        .navigationTitle("Ktor Networking")
    }
}

struct TodoRowView: View {
    let todo: Todo
    
    var body: some View {
        HStack {
            Image(systemName: todo.completed ? "checkmark.circle.fill" : "circle")
            Text(todo.title)
        }
    }
}
