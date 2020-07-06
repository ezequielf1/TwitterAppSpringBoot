package com.etermax.twitter.domain.users.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FollowRequest {
    private final String followerUsername;
    private final String followedUsername;
}
