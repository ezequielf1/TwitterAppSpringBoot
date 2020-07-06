package com.etermax.twitter.presentation;

import com.etermax.twitter.domain.users.requests.AskFollowRequest;
import com.etermax.twitter.domain.users.requests.FollowRequest;
import com.etermax.twitter.domain.users.FollowUser;
import com.etermax.twitter.domain.users.User;
import com.etermax.twitter.domain.users.actions.AskFollowsAction;
import com.etermax.twitter.domain.users.repository.UserRepository;
import com.etermax.twitter.domain.users.actions.CreateUserAction;
import com.etermax.twitter.domain.users.actions.FollowUserAction;
import com.etermax.twitter.domain.users.actions.UpdateUserAction;
import com.etermax.twitter.infraestructure.UserRepositoryMemory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/user")
public class UsersAPI {
    private final UserRepository userRepository;

    public UsersAPI() {
        userRepository = new UserRepositoryMemory();
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User newUser) {
        return new CreateUserAction(userRepository).createUser(newUser);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateUser(@RequestBody User user) {
        new UpdateUserAction(userRepository).updateUser(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/follow")
    public User followUser(@RequestBody FollowRequest followRequest) {
        return new FollowUserAction(userRepository).follow(followRequest);
    }

    @GetMapping("/askfollow")
    public ArrayList<FollowUser> askFollows(@RequestBody AskFollowRequest request) {
        return new AskFollowsAction(userRepository).getFollowingsOf(request.getUsername());
    }
}
