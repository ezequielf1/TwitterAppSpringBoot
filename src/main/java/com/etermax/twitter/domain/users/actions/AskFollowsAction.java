package com.etermax.twitter.domain.users.actions;

import com.etermax.twitter.domain.users.FollowUser;
import com.etermax.twitter.domain.users.User;
import com.etermax.twitter.domain.users.repository.UserRepository;

import java.util.HashMap;

public class AskFollowsAction {
    private final UserRepository userRepository;

    public AskFollowsAction(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public HashMap<String, FollowUser> getFollowingsOf(User user) {
        return user.getFollowings();
    }
}
