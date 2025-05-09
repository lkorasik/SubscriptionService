package com.example.SubscriptionService.api.subscription;

import java.util.List;

public record GetTopSubscriptionsResponseDto(
        List<GetTopSubscriptionsEntryResponseDto> subscriptions
) {
}

