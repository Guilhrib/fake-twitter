package com.ribeiro.faketwitter.controller.dto;

import org.springframework.data.domain.Page;

import java.util.List;

public record FeedResponse(
        List<FeedItem> items,
        int page,
        int pageSize,
        int totalPages,
        long totalElements
) {
    public FeedResponse(Page<FeedItem> page) {
        this(
            page.getContent(),
            page.getNumber(),
            page.getSize(),
            page.getTotalPages(),
            page.getTotalElements()
        );
    }
}
