//
//  TitleBarView.swift
//  iosApp
//
//  Created by An Tran on 19/1/22.
//  Copyright © 2022 orgName. All rights reserved.
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
