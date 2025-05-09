package com.example.SubscriptionService;

import com.example.SubscriptionService.api.subscription.CreateSubscriptionRequestDto;
import com.example.SubscriptionService.data.entity.Subscription;
import com.example.SubscriptionService.data.entity.User;
import com.example.SubscriptionService.data.repository.SubscriptionRepository;
import com.example.SubscriptionService.service.exception.SubscriptionNotFoundException;
import com.example.SubscriptionService.service.SubscriptionService;
import com.example.SubscriptionService.service.UserService;
import com.example.SubscriptionService.service.exception.IncorrectSubscriptionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class SubscriptionServiceTest {
    @Mock
    private SubscriptionRepository subscriptionRepository;
    @Mock
    private UserService userService;
    @InjectMocks
    private SubscriptionService subscriptionService;

    @Test
    public void createSubscription() {
        CreateSubscriptionRequestDto dto = new CreateSubscriptionRequestDto("L");

        subscriptionService.createSubscription(1, dto);
    }

    @Test
    public void createSubscriptionIncorrect() {
        CreateSubscriptionRequestDto dto = new CreateSubscriptionRequestDto(null);

        Assertions.assertThrows(IncorrectSubscriptionException.class, () -> subscriptionService.createSubscription(1, dto));
    }

    @Test
    public void getSubscriptions() {
        Subscription expectedSubscription = new Subscription();
        String link = "Q";
        expectedSubscription.setLink(link);
        long userId = 1;
        User user = new User();
        user.setId(userId);
        user.setSubscriptions(new ArrayList<>());
        user.addSubscription(expectedSubscription);

        Mockito.doReturn(user).when(userService).findUser(userId);

        List<Subscription> subscriptions = subscriptionService.getSubscriptions(userId);

        Assertions.assertEquals(1, subscriptions.size());
        Assertions.assertEquals(link, subscriptions.get(0).getLink());
        Assertions.assertEquals(user, subscriptions.get(0).getUser());
    }

    @Test
    public void deleteSubscription() {
        Subscription subscription = new Subscription();
        String link = "Q";
        long subscriptionId = 1;
        subscription.setLink(link);
        long userId = 1;
        User user = new User();
        user.setId(userId);
        user.setSubscriptions(new ArrayList<>());
        user.addSubscription(subscription);

        Mockito.doReturn(Optional.of(subscription)).when(subscriptionRepository).findById(subscriptionId);

        subscriptionService.deleteSubscription(subscriptionId);

        Assertions.assertEquals(0, user.getSubscriptions().size());
    }

    @Test
    public void deleteSubscriptionNotExists() {
        long subscriptionId = 1;
        long userId = 1;
        User user = new User();
        user.setId(userId);
        user.setSubscriptions(new ArrayList<>());

        Mockito.doReturn(Optional.empty()).when(subscriptionRepository).findById(subscriptionId);

        Assertions.assertThrows(SubscriptionNotFoundException.class, () -> subscriptionService.deleteSubscription(subscriptionId));
    }
}
