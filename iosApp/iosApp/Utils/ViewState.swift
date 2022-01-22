//
// Copyright © 2022 An Tran. All rights reserved.
//

import Foundation

enum ViewState<T> {
    case loading
    case error
    case loaded(_ data: T)
}
