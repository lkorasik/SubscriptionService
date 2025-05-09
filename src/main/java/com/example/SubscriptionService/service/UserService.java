package com.example.SubscriptionService.service;

import com.example.SubscriptionService.api.user.CreateUserRequestDto;
import com.example.SubscriptionService.api.user.UpdateUserRequestDto;
import com.example.SubscriptionService.data.entity.Subscription;
import com.example.SubscriptionService.data.entity.User;
import com.example.SubscriptionService.data.repository.UserRepository;
import com.example.SubscriptionService.service.exception.CannotCreateUserEmptyNameException;
import com.example.SubscriptionService.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public long createUser(CreateUserRequestDto dto) {
        if (dto.name() == null) {
            throw new CannotCreateUserEmptyNameException();
        }

        User user = new User();
        user.setName(dto.name());

        user = userRepository.save(user);

        return user.getId();
    }

    public User findUser(long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public void updateUser(long id, UpdateUserRequestDto dto) {
        User user = findUser(id);

        user.setName(dto.name());

        if (dto.subscriptions() != null) {
            user.deleteAllSubscriptions();
            dto.subscriptions().forEach(link -> {
                Subscription subscription = new Subscription();
                subscription.setLink(link.link());

                user.addSubscription(subscription);
            });
        }

        userRepository.save(user);
    }

    public void deleteUser(long id){
        userRepository.deleteById(id);
    }
}
