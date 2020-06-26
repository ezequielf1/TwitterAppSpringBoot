package com.etermax.twitter.domain.users;

import lombok.*;

import java.util.HashMap;

@NoArgsConstructor
@Data
public class User {
    private String username;
    private String realName;
    private HashMap<String, FollowUser> followings = new HashMap<>();
    private HashMap<String, FollowUser> followers = new HashMap<>();

    public User(String username, String realName) {
        this.username = username;
        this.realName = realName;
    }

    public void addFollowing(FollowUser followed) {
        followings.put(followed.getUsername(), followed);
    }

    public void addFollower(FollowUser follower) {
        followers.put(follower.getUsername(), follower);
    }
}
