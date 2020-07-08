package com.etermax.twitter.domain.users;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FollowUser {
    private String username;

    public static FollowUser buildFrom(User user) {
        return new FollowUser(user.getUsername());
    }
}
