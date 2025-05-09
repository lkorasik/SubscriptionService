package com.example.SubscriptionService.api.user;

import com.example.SubscriptionService.data.entity.User;
import com.example.SubscriptionService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public CreateUserResponseDto createUser(@RequestBody CreateUserRequestDto dto) {
        return new CreateUserResponseDto(userService.createUser(dto));
    }

    @GetMapping("/users/{id}")
    public GetUserResponseDto getUser(@PathVariable("id") long id) {
        User user = userService.findUser(id);

        return new GetUserResponseDto(
                user.getId(),
                user.getName(),
                user.getSubscriptions()
                        .stream()
                        .map(x -> new SubscriptionDto(x.getId(), x.getLink()))
                        .toList()
        );
    }

    @PutMapping("/users/{id}")
    public void updateUser(@PathVariable("id") long id, @RequestBody UpdateUserRequestDto dto) {
        userService.updateUser(id, dto);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
    }
}
