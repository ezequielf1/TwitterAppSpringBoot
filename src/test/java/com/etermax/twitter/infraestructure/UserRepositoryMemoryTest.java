package com.etermax.twitter.infraestructure;

import com.etermax.twitter.domain.users.User;
import com.etermax.twitter.domain.users.repository.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.etermax.twitter.domain.users.UserBuilder.aUser;
import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryMemoryTest {

    private UserRepositoryMemory userRepository;
    private final String USERNAME = "Overterror";
    private final String REAL_NAME = "Ezequiel";
    private final String UPDATED_REAL_NAME = "Brian";
    private final User user = aUser().withUsername(USERNAME).withRealName(REAL_NAME).build();

    @BeforeEach
    public void setUp() {
        userRepository = new UserRepositoryMemory();
    }

    @Test
    public void whenUserIsSavedThenGetUserByIdReturnsTheUser() {
        userRepository.save(user);

        User savedUser = userRepository.getUserById(user.getUsername());

        assertEquals(savedUser, user);
    }

    @Test
    public void whenUserIsNotSavedThenGetUserByIdReturnsNull() {
        assertEquals(null, userRepository.getUserById(user.getUsername()));
    }

    @Test
    public void whenUserIsRegisteredThenIsUserRegisteredReturnsTrue() {
        userRepository.save(user);
        assertEquals(true, userRepository.isUserRegistered(user));
    }

    @Test
    public void whenUserIsNotRegisteredThenIsUserRegisteredReturnFalse() {
        assertEquals(false, userRepository.isUserRegistered(user));
    }

    @Test
    public void whenUserIsSavedThenRepositoryContainsTheUser() {
        userRepository.save(user);
        assertEquals(true, userRepository.getUsers().containsValue(user));
    }

    @Test
    public void whenUserIsUpdatedThenTheRepositoryContainsTheNewUpdatedUser() {
        userRepository.save(user);
        User updatedUser = aUser().withUsername(USERNAME).withRealName(UPDATED_REAL_NAME).build();

        userRepository.update(updatedUser);

        assertEquals(true, userRepository.getUsers().containsValue(updatedUser));
    }
}