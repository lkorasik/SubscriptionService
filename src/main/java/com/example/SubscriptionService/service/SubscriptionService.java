package com.example.SubscriptionService.service;

import com.example.SubscriptionService.api.subscription.CreateSubscriptionRequestDto;
import com.example.SubscriptionService.data.entity.Subscription;
import com.example.SubscriptionService.data.repository.SubscriptionRepository;
import com.example.SubscriptionService.data.entity.User;
import com.example.SubscriptionService.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService {
    private static final int TOP_LENGTH = 3;
    private final SubscriptionRepository subscriptionRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository, UserService userService, UserRepository userRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public void createSubscription(long userId, CreateSubscriptionRequestDto dto) {
        User user = userService.findUser(userId);

        Subscription subscription = new Subscription();
        subscription.setLink(dto.link());

        user.addSubscription(subscription);

        userRepository.save(user);
    }

    public List<Subscription> getSubscriptions(long userId) {
        User user = userService.findUser(userId);
        return user.getSubscriptions();
    }

    public void deleteSubscription(long id) {
        Subscription subscription = subscriptionRepository.getReferenceById(id);
        subscription.getUser().deleteSubscription(subscription);
        subscriptionRepository.deleteById(id);
    }

    public List<SubscriptionRepository.SubscriptionInfo> getTopSubscriptions() {
        return subscriptionRepository.getTopK(TOP_LENGTH);
    }
}
