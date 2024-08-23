package com.ribeiro.faketwitter.service;

import com.ribeiro.faketwitter.controller.dto.CreateTweet;
import com.ribeiro.faketwitter.controller.dto.FeedResponse;

public interface TweetService {
    void create(CreateTweet dto, String userId);

    void delete(Long tweetId, String userId);

    FeedResponse getFeed(int page, int pageSize);
}
