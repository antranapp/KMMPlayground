//
// Copyright © 2022 An Tran. All rights reserved.
//

import Foundation
import SwiftUI
import shared
import BetterSafariView

struct Day8ContentView: View {
    @StateObject private var viewModel = Day8FeedViewModel()
    
    @State private var url: URL?
    
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
                        ForEach(posts, id: \.self) { post in
                            PostRowView(post: post)
                                .onTapGesture {
                                    post.link.map {
                                        url = URL(string: $0)
                                    }
                                }
                            Divider()
                        }
                    }
                }
            }
        }
        .safariView(
            item: $url,
            onDismiss: {
                url = nil
            }
        ) { url in
            SafariView(
                url: url,
                configuration: SafariView.Configuration(
                    entersReaderIfAvailable: false,
                    barCollapsingEnabled: true
                )
            )
            .dismissButtonStyle(.done)
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
