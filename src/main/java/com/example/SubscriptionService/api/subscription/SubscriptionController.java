package com.example.SubscriptionService.api.subscription;

import com.example.SubscriptionService.service.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubscriptionController {
    private final static Logger logger = LoggerFactory.getLogger(SubscriptionController.class);
    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/users/{id}/subscriptions")
    public void createSubscription(@PathVariable("id") long id, @RequestBody CreateSubscriptionRequestDto dto) {
        logger.info("Create subscription. User id: {}. Subscription: {}", id, dto);
        subscriptionService.createSubscription(id, dto);
    }

    @GetMapping("/users/{id}/subscriptions")
    public List<GetSubscriptionsResponseDto> getSubscriptions(@PathVariable("id") long id) {
        logger.info("Get subscriptions. User id: {}", id);
        return subscriptionService.getSubscriptions(id)
                .stream()
                .map(x -> new GetSubscriptionsResponseDto(x.getId(), x.getLink()))
                .toList();
    }

    @DeleteMapping("/users/{id}/subscriptions/{sub_id}")
    public void deleteSubscription(@PathVariable("id") long id, @PathVariable("sub_id") long subscriptionId) {
        logger.info("Delete subscription. User id: {}. Subscription id: {}", id, subscriptionId);
        subscriptionService.deleteSubscription(subscriptionId);
    }

    @GetMapping("/subscriptions/top")
    public GetTopSubscriptionsResponseDto getTopSubscriptions() {
        logger.info("Get tio subscriptions");
        List<GetTopSubscriptionsEntryResponseDto> subscriptions = subscriptionService.getTopSubscriptions()
                .stream()
                .map(x -> new GetTopSubscriptionsEntryResponseDto(x.getLink()))
                .toList();
        return new GetTopSubscriptionsResponseDto(subscriptions);
    }
}
