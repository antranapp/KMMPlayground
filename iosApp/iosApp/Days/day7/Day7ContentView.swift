//
// Copyright © 2022 An Tran. All rights reserved.
//

import Foundation
import SwiftUI
import shared
import BetterSafariView

struct Day7ContentView: View {
    @StateObject private var viewModel = Day7FeedViewModel()
    
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
        .navigationTitle("RSS Reader")
    }
}

struct PostRowView: View {
    let post: Post
    
    var body: some View {
        VStack(alignment: .leading) {
            Text(post.title)
                .font(.title)
            if let imageUrl = post.imageUrl,
               let url = URL(string: imageUrl) {
                AsyncImage(url: url) { phase in
                    if let image = phase.image {
                        image
                            .resizable()
                            .aspectRatio(contentMode: .fit)
                    } else if phase.error != nil {
                        Color.red // Indicates an error.
                    } else {
                        Color.blue // Acts as a placeholder.
                    }
                }
            }
            if let description = post.desc {
                Text(description)
            }
        }
        .padding()
    }
}
