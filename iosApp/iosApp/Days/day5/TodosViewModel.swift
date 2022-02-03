//
// Copyright © 2022 An Tran. All rights reserved.
//

import Combine
import Foundation
import shared

class TodosViewModel: ObservableObject {
    private let sdk = shared.TodoSDK()
    
    @Published var state: ViewState<[Todo]> = .loading
    
    func load() {
        state = .loading
        sdk.getTodos { [weak self] todos, error in
            if let todos = todos {
                self?.state = .loaded(todos)
            } else {
                self?.state = .error(error)
            }
        }
    }
}
