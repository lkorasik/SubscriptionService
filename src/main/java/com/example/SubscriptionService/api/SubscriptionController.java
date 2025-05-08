package com.example.SubscriptionService.api;

import org.springframework.web.bind.annotation.*;

@RestController
public class SubscriptionController {
    @PostMapping("/users/{id}/subscriptions")
    public void createSubscription(@PathVariable("id") long id) {
        throw new RuntimeException("Not implemented yet");
    }

    @GetMapping("/users/{id}/subscriptions")
    public void getSubscriptions(@PathVariable("id") long id) {
        throw new RuntimeException("Not implemented yet");
    }

    @DeleteMapping("/users/{id}/subscriptions/{sub_id}")
    public void deleteSubscription(@PathVariable("id") long id, @PathVariable("sub_id") long subscriptionId) {
        throw new RuntimeException("Not implemented yet");
    }

    @GetMapping("/subscriptions/top")
    public void getTopSubscriptions() {
        throw new RuntimeException("Not implemented yet");
    }
}
