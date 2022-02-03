//
// Copyright © 2022 An Tran. All rights reserved.
//

import Foundation

enum ViewState<T> {
    case loading
    case error(_ error: Error? = nil)
    case loaded(_ data: T)
}
