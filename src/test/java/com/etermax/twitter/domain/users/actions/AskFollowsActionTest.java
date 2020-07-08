package com.etermax.twitter.domain.users.actions;

import com.etermax.twitter.domain.users.requests.FollowRequest;
import com.etermax.twitter.domain.users.FollowUser;
import com.etermax.twitter.domain.users.User;
import com.etermax.twitter.domain.users.repository.UserRepository;
import com.etermax.twitter.infraestructure.repository.UserRepositoryDataBase;
import com.etermax.twitter.infraestructure.repository.UserRepositoryMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;

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

    @BeforeEach
    void setUp() {
        userRepository = new UserRepositoryMemory();
        followUserAction = new FollowUserAction(userRepository);
        createUserAction = new CreateUserAction(userRepository);
        askFollowsAction = new AskFollowsAction(userRepository);
    }

    @Test
    public void whenAskWhoIsAnUserFollowingThenReturnsWhoIsTheUserFollowing() {
        givenFirstUserFollowSecondUser();

        HashSet<String> followings = askFollowsAction.getFollowingsOf(FOLLOWER_USERNAME);

        assertEquals(1, followings.size());
        assertEquals(true, followings.contains(FOLLOWED_USERNAME));
    }

    private void givenFirstUserFollowSecondUser() {
        createUserAction.createUser(FIRST_USER);
        createUserAction.createUser(SECOND_USER);
        followUserAction.follow(FOLLOWER_USERNAME, FOLLOWED_USERNAME);
    }
}