package com.example.SubscriptionService.api.user;

import com.example.SubscriptionService.api.subscription.CreateSubscriptionRequestDto;

import java.util.List;

public record UpdateUserRequestDto(
        String name,
        List<CreateSubscriptionRequestDto> subscriptions
) {
}
