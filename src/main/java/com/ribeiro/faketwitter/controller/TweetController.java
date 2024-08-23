package com.ribeiro.faketwitter.controller;

import com.ribeiro.faketwitter.controller.dto.CreateTweet;
import com.ribeiro.faketwitter.controller.dto.FeedResponse;
import com.ribeiro.faketwitter.service.TweetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tweet")
public class TweetController {
    private final TweetService tweetService;

    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateTweet dto, JwtAuthenticationToken token) {
        tweetService.create(dto, token.getName());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{tweetId}")
    public ResponseEntity<Void> delete(@PathVariable("tweetId") Long tweetId, JwtAuthenticationToken token) {
        tweetService.delete(tweetId, token.getName());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<FeedResponse> feed(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize
    ) {
        var feed = tweetService.getFeed(page, pageSize);
        return ResponseEntity.ok().body(feed);
    }
}
