package com.example.SubscriptionService.api.user;

import java.util.List;

public record UpdateUserRequestDto(
        String name,
        List<Long> subscriptionIds
) {
}
