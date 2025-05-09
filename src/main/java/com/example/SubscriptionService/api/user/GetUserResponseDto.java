package com.example.SubscriptionService.api.user;

import java.util.List;

public record GetUserResponseDto(
        long id,
        String name,
        List<SubscriptionDto> subscriptions
) {
}
