package com.example.SubscriptionService.service;

import com.example.SubscriptionService.api.subscription.CreateSubscriptionRequestDto;
import com.example.SubscriptionService.data.entity.Subscription;
import com.example.SubscriptionService.data.repository.SubscriptionRepository;
import com.example.SubscriptionService.data.entity.User;
import com.example.SubscriptionService.service.exception.IncorrectSubscriptionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService {
    private static final int TOP_LENGTH = 3;
    private final SubscriptionRepository subscriptionRepository;
    private final UserService userService;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository, UserService userService) {
        this.subscriptionRepository = subscriptionRepository;
        this.userService = userService;
    }

    public void createSubscription(long userId, CreateSubscriptionRequestDto dto) {
        Subscription subscription = new Subscription();

        if ((dto.link() == null) || dto.link().isBlank()) {
            throw new IncorrectSubscriptionException();
        }
        subscription.setLink(dto.link());

        userService.addSubscription(userId, subscription);
    }

    public List<Subscription> getSubscriptions(long userId) {
        User user = userService.findUser(userId);
        return user.getSubscriptions();
    }

    public void deleteSubscription(long id) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new SubscriptionNotFoundException(id));
        subscription.getUser().deleteSubscription(subscription);
        subscriptionRepository.deleteById(id);
    }

    public List<SubscriptionRepository.SubscriptionInfo> getTopSubscriptions() {
        return subscriptionRepository.getTopK(TOP_LENGTH);
    }
}
