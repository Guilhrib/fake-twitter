package com.ribeiro.faketwitter.controller.dto;

import com.ribeiro.faketwitter.entity.Tweet;

public record FeedItem(
        Long tweetId,
        String content,
        String username
) {
    public FeedItem(Tweet tweet) {
        this(tweet.getTweetId(), tweet.getContent(), tweet.getUser().getUsername());
    }
}
