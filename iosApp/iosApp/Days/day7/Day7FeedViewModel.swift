//
//  FeedViewModel.swift
//  iosApp
//
//  Created by An Tran on 25/1/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared
import Combine

class Day7FeedViewModel: ObservableObject {
    private let rssReader = shared.RssReader.Companion().create(withLog: true)
    
    @Published var state: ViewState<[Post]> = .loading
    
    func load() {
        state = .loading
        rssReader.getAllFeeds { [weak self] feed, error in
            if let feed = feed {
                self?.state = .loaded(feed.posts)
            } else {
                self?.state = .error
            }
        }
    }
}
