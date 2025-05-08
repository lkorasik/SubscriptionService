package com.example.SubscriptionService.service;

import com.example.SubscriptionService.api.CreateUserRequestDto;
import com.example.SubscriptionService.api.UpdateUserRequestDto;
import com.example.SubscriptionService.data.User;
import com.example.SubscriptionService.data.UserRepository;
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

        //  todo: do sometyhing with subscriptions
        userRepository.save(user);
    }

    public void deleteUser(long id){
        userRepository.deleteById(id);
    }
}
