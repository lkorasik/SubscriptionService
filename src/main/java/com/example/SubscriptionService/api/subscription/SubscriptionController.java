package com.example.SubscriptionService.api.subscription;

import com.example.SubscriptionService.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/users/{id}/subscriptions")
    public void createSubscription(@PathVariable("id") long id, @RequestBody CreateSubscriptionRequestDto dto) {
        subscriptionService.createSubscription(id, dto);
    }

    @GetMapping("/users/{id}/subscriptions")
    public List<GetSubscriptionsResponseDto> getSubscriptions(@PathVariable("id") long id) {
        return subscriptionService.getSubscriptions(id)
                .stream()
                .map(x -> new GetSubscriptionsResponseDto(x.getId(), x.getLink()))
                .toList();
    }

    @DeleteMapping("/users/{id}/subscriptions/{sub_id}")
    public void deleteSubscription(@PathVariable("id") long id, @PathVariable("sub_id") long subscriptionId) {
        subscriptionService.deleteSubscription(subscriptionId);
        // todo; переделай
    }

    @GetMapping("/subscriptions/top")
    public GetTopSubscriptionsResponseDto getTopSubscriptions() {
        List<GetTopSubscriptionsEntryResponseDto> subscriptions = subscriptionService.getTopSubscriptions()
                .stream()
                .map(x -> new GetTopSubscriptionsEntryResponseDto(x.getLink()))
                .toList();
        return new GetTopSubscriptionsResponseDto(subscriptions);
    }
}
