//
// Copyright © 2022 An Tran. All rights reserved.
//

import Foundation
import SwiftUI

struct TitleBarView: View {
    let title: String

    var body: some View {
        VStack {
            Text(title)
                .font(.subheadline)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(Color.white)
    }
}
