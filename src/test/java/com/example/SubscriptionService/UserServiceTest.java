package com.example.SubscriptionService;

import com.example.SubscriptionService.api.subscription.CreateSubscriptionRequestDto;
import com.example.SubscriptionService.api.user.CreateUserRequestDto;
import com.example.SubscriptionService.api.user.UpdateUserRequestDto;
import com.example.SubscriptionService.data.entity.Subscription;
import com.example.SubscriptionService.data.entity.User;
import com.example.SubscriptionService.data.repository.UserRepository;
import com.example.SubscriptionService.service.UserService;
import com.example.SubscriptionService.service.exception.CannotCreateUserEmptyNameException;
import com.example.SubscriptionService.service.exception.UserNotFoundException;
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
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @Test
    public void createUser() {
        CreateUserRequestDto dto = new CreateUserRequestDto("Name");

        User user = new User();
        user.setId(1);

        Mockito.doReturn(user).when(userRepository).save(Mockito.any());

        userService.createUser(dto);
    }

    @Test
    public void createUserNoName() {
        CreateUserRequestDto dto = new CreateUserRequestDto(null);

        Assertions.assertThrows(CannotCreateUserEmptyNameException.class, () -> userService.createUser(dto));
    }

    @Test
    public void findUser() {
        long userId = 1;
        User expectedUser = new User();
        expectedUser.setId(userId);
        expectedUser.setName("Q");

        Mockito.doReturn(Optional.of(expectedUser)).when(userRepository).findById(Mockito.anyLong());

        User actualUser = userService.findUser(userId);

        Assertions.assertEquals(expectedUser, actualUser);
    }

    @Test
    public void findUserNotExists() {
        long userId = 1;

        Mockito.doReturn(Optional.empty()).when(userRepository).findById(Mockito.anyLong());

        Assertions.assertThrows(UserNotFoundException.class, () -> userService.findUser(userId));
    }

    @Test
    public void updateUser() {
        long userId = 1;
        User user = new User();
        user.setId(userId);
        user.setName("Q");
        user.setSubscriptions(new ArrayList<>());
        UpdateUserRequestDto dto = new UpdateUserRequestDto("W", List.of(new CreateSubscriptionRequestDto("q")));

        Mockito.doReturn(Optional.of(user)).when(userRepository).findById(Mockito.anyLong());

        userService.updateUser(userId, dto);

        Assertions.assertEquals("W", user.getName());
        Assertions.assertEquals(1, user.getSubscriptions().size());
        Assertions.assertEquals("q", user.getSubscriptions().get(0).getLink());
    }

    @Test
    public void updateUserNoChangeSubscription() {
        long userId = 1;
        User user = new User();
        user.setId(userId);
        user.setName("Q");
        user.setSubscriptions(new ArrayList<>());
        Subscription subscription = new Subscription();
        subscription.setLink("q");
        user.addSubscription(subscription);
        UpdateUserRequestDto dto = new UpdateUserRequestDto("W", null);

        Mockito.doReturn(Optional.of(user)).when(userRepository).findById(Mockito.anyLong());

        userService.updateUser(userId, dto);

        Assertions.assertEquals("W", user.getName());
        Assertions.assertEquals(1, user.getSubscriptions().size());
        Assertions.assertEquals("q", user.getSubscriptions().get(0).getLink());
    }

    @Test
    public void updateUserDeleteSubscription() {
        long userId = 1;
        User user = new User();
        user.setId(userId);
        user.setName("Q");
        user.setSubscriptions(new ArrayList<>());
        Subscription subscription = new Subscription();
        subscription.setLink("q");
        user.addSubscription(subscription);
        UpdateUserRequestDto dto = new UpdateUserRequestDto("W", List.of());

        Mockito.doReturn(Optional.of(user)).when(userRepository).findById(Mockito.anyLong());

        userService.updateUser(userId, dto);

        Assertions.assertEquals("W", user.getName());
        Assertions.assertEquals(0, user.getSubscriptions().size());
    }

    @Test
    public void deleteUser() {
        long userId = 1;
        User user = new User();
        user.setId(userId);
        user.setName("Q");
        user.setSubscriptions(new ArrayList<>());
        Subscription subscription = new Subscription();
        subscription.setLink("q");
        user.addSubscription(subscription);

        userService.deleteUser(userId);
    }
}
