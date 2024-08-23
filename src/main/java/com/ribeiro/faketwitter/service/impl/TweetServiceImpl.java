package com.ribeiro.faketwitter.service.impl;

import com.ribeiro.faketwitter.controller.dto.CreateTweet;
import com.ribeiro.faketwitter.controller.dto.FeedItem;
import com.ribeiro.faketwitter.controller.dto.FeedResponse;
import com.ribeiro.faketwitter.entity.Tweet;
import com.ribeiro.faketwitter.exception.InvalidActionException;
import com.ribeiro.faketwitter.exception.ResourceNotFoundException;
import com.ribeiro.faketwitter.repository.TweetRepository;
import com.ribeiro.faketwitter.service.TweetService;
import com.ribeiro.faketwitter.service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class TweetServiceImpl implements TweetService {
    private final TweetRepository tweetRepository;
    private final UserService userService;

    public TweetServiceImpl(TweetRepository tweetRepository, UserService userService) {
        this.tweetRepository = tweetRepository;
        this.userService = userService;
    }

    @Override
    public void create(CreateTweet dto, String userId) {
        var user = userService.findById(userId);
        var tweet = new Tweet(dto.content(), user);
        tweetRepository.save(tweet);
    }

    @Override
    public void delete(Long tweetId, String userId) {
        var user = userService.findById(userId);
        var tweet = tweetRepository
                .findById(tweetId)
                .orElseThrow(() -> new ResourceNotFoundException("tweet"));

        if (user.isAdmin() || tweet.getUser().getUserId() == user.getUserId()) {
            tweetRepository.delete(tweet);
        }

        throw new InvalidActionException("cannot delete this tweet");
    }

    @Override
    public FeedResponse getFeed(int page, int pageSize) {
        var tweets = tweetRepository.findAll(PageRequest.of(page, pageSize)).map(FeedItem::new);
        return new FeedResponse(tweets);
    }
}
