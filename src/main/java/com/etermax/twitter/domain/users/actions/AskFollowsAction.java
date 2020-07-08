package com.etermax.twitter.domain.users.actions;

import com.etermax.twitter.domain.users.FollowUser;
import com.etermax.twitter.domain.users.User;
import com.etermax.twitter.domain.users.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class AskFollowsAction {
    private final UserRepository userRepository;

    public AskFollowsAction(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public HashSet<String> getFollowingsOf(String username) {
        return userRepository.getUserById(username).getFollowings();
    }
}
