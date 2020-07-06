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

    public User follow(FollowRequest followRequest) {
        User follower = userRepository.getUserById(followRequest.getFollowerUsername());
        User followed = userRepository.getUserById(followRequest.getFollowedUsername());
        handleFollows(follower, followed);
        userRepository.update(followed);
        return userRepository.update(follower);
    }

    private void handleFollows(User follower, User followed) {
        follower.addFollowing(FollowUser.buildFrom(followed));
        followed.addFollower(FollowUser.buildFrom(follower));
    }
}
