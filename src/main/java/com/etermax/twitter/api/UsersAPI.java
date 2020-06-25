package com.etermax.twitter.api;

import com.etermax.twitter.domain.users.User;
import com.etermax.twitter.domain.users.UserService;
import com.etermax.twitter.domain.users.UsernameAlreadyInUseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/user")
public class UsersAPI {
    private final UserService userService;

    @Autowired
    public UsersAPI(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User newUser) {
        return userService.createUser(newUser);
    }
}
