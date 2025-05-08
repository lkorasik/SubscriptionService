package com.example.SubscriptionService.api;

import java.util.List;

public record GetUserResponseDto(
        long id,
        String name,
        List<SubscriptionDto> subscriptions
) {
}
