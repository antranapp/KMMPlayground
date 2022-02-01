//
//  FeedViewModel.swift
//  iosApp
//
//  Created by An Tran on 2/2/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

class Day8FeedViewModel: ObservableObject {
    private let rssReader = shared.RssReader2.Companion().create(withLog: true)
    
    @Published var state: ViewState<[Post]> = .loading
    
    func load() {
        state = .loading
        rssReader.getAllFeeds(forceUpdate: true) { [weak self] feeds, error in
            if let feeds = feeds {
                let posts = feeds.flatMap(\.posts)
                self?.state = .loaded(posts)
            } else {
                self?.state = .error
            }
        }
    }
}
