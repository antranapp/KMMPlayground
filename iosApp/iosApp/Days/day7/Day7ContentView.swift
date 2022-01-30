//
// Copyright © 2022 An Tran. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct Day7ContentView: View {
    @StateObject private var viewModel = FeedViewModel()
    
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
            case .loaded(let feed):
                List(feed.posts, id: \.self) {
                    PostRowView(post: $0)
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
        .navigationTitle("RSS Reader")
    }
}

struct PostRowView: View {
    let post: Post
    
    var body: some View {
        Text(post.title)
    }
}
