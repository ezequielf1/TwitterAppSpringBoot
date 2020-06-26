package com.etermax.twitter.domain.users.actions;

import com.etermax.twitter.domain.users.FollowRequest;
import com.etermax.twitter.domain.users.FollowUser;
import com.etermax.twitter.domain.users.User;
import com.etermax.twitter.domain.users.repository.UserRepository;
import com.etermax.twitter.infraestructure.UserRepositoryMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static com.etermax.twitter.domain.users.UserBuilder.aUser;
import static org.junit.jupiter.api.Assertions.*;

class AskFollowsActionTest {

    private FollowUserAction followUserAction;
    private CreateUserAction createUserAction;
    private AskFollowsAction askFollowsAction;
    private UserRepository userRepository;
    private final String FOLLOWER_USERNAME = "Overterror";
    private final String FOLLOWED_USERNAME = "Stoolgear";
    private final User FIRST_USER = aUser().withUsername(FOLLOWER_USERNAME).withRealName("Pepe").build();
    private final User SECOND_USER = aUser().withUsername(FOLLOWED_USERNAME).withRealName("Pedro").build();
    private final FollowRequest FOLLOW_REQUEST = new FollowRequest(FOLLOWER_USERNAME, FOLLOWED_USERNAME);

    @BeforeEach
    void setUp() {
        userRepository = new UserRepositoryMemory();
        followUserAction = new FollowUserAction(userRepository);
        createUserAction = new CreateUserAction(userRepository);
        askFollowsAction = new AskFollowsAction(userRepository);
    }

    @Test
    public void whenAskWhoIsAnUserFollowingThenReturnsWhoIsTheUserFollowing() {
        performFollowAction();

        HashMap<String, FollowUser> followings = askFollowsAction.getFollowingsOf(FIRST_USER);

        assertEquals(1, followings.size());
        assertEquals(true, followings.containsKey(FOLLOWED_USERNAME));
    }

    private void performFollowAction() {
        createUserAction.createUser(FIRST_USER);
        createUserAction.createUser(SECOND_USER);
        followUserAction.follow(FOLLOW_REQUEST);
    }
}