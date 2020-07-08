package com.etermax.twitter.domain.users.actions;

import com.etermax.twitter.domain.users.requests.FollowRequest;
import com.etermax.twitter.domain.users.FollowUser;
import com.etermax.twitter.domain.users.User;
import com.etermax.twitter.domain.users.repository.UserRepository;

public class FollowUserAction {
    private final UserRepository userRepository;

    public FollowUserAction(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User follow(String followerUsername, String followedUsername) {
        return userRepository.startFollow(followerUsername, followedUsername);
    }
}
