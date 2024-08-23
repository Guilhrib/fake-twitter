package com.ribeiro.faketwitter.controller.dto;

import java.util.List;

public record Feed(
        List<FeedItem> items,
        int page,
        int pageSize,
        int totalPages,
        int totalElements
) {
}
