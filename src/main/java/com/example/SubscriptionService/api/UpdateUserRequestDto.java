package com.example.SubscriptionService.api;

import java.util.List;

public record UpdateUserRequestDto(
        String name,
        List<Long> subscriptionIds
) {
}
