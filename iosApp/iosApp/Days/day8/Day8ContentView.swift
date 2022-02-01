//
// Copyright © 2022 An Tran. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct Day8ContentView: View {
    @StateObject private var viewModel = Day8FeedViewModel()
    
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
            case .loaded(let posts):
                ScrollView {
                    LazyVStack {
                        ForEach(posts, id: \.self) {
                            PostRowView(post: $0)
                            
                            Divider()
                        }
                    }
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
        .navigationTitle("Multisource RSS Reader")
    }
}
