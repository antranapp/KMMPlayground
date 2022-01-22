//
// Copyright © 2022 An Tran. All rights reserved.
//

import shared
import SwiftUI

struct Day5ContentView: View {
    @StateObject private var viewModel = ViewModel()
    
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

private struct TodoRowView: View {
    let todo: Todo
    
    var body: some View {
        HStack {
            Image(systemName: todo.completed ? "checkmark.circle.fill" : "circle")
            Text(todo.title)
        }
    }
}

private extension Day5ContentView {
    class ViewModel: ObservableObject {
        private let sdk = shared.TodoSDK()
        
        @Published var state: ViewState<[Todo]> = .loading
        
        func load() {
            state = .loading
            sdk.getTodos { [weak self] todos, error in
                if let todos = todos {
                    self?.state = .loaded(todos)
                } else {
                    self?.state = .error
                }
            }
        }
    }
}
