package com.etermax.twitter.domain.users;

import lombok.*;

import java.util.HashMap;
import java.util.HashSet;

@NoArgsConstructor
@Data
public class User {
    private String username;
    private String realName;
    private HashSet<String> followings = new HashSet<>();
    private HashSet<String> followers = new HashSet<>();

    public User(String username, String realName) {
        this.username = username;
        this.realName = realName;
    }

    public void addFollowing(String username) {
        followings.add(username);
    }

    public void addFollower(String username) {
        followers.add(username);
    }
}
