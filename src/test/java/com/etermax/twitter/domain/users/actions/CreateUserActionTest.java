package com.etermax.twitter.domain.users.actions;

import com.etermax.twitter.domain.users.User;
import com.etermax.twitter.domain.users.exceptions.UsernameAlreadyInUseException;
import com.etermax.twitter.domain.users.repository.UserRepository;
import com.etermax.twitter.infraestructure.repository.UserRepositoryMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.etermax.twitter.domain.users.UserBuilder.aUser;
import static org.junit.jupiter.api.Assertions.*;

class CreateUserActionTest {

    private UserRepository userRepository;
    private CreateUserAction createUserAction;
    private final String USERNAME = "Overterror";
    private final String REAL_NAME = "Ezequiel";
    private final User USER = aUser().withUsername(USERNAME).withRealName(REAL_NAME).build();

    @BeforeEach
    public void setUp() {
        userRepository = new UserRepositoryMemory();
        createUserAction = new CreateUserAction(userRepository);
    }

    @Test
    public void whenUserIsCreatedThenReturnsTheNewUser() {
        User createdUser = createUserAction.createUser(USER);
        assertEquals(USER, createdUser);
    }

    @Test
    public void whenUserAlreadyExistsThenReturnUsernameAlreadyInUse() {
        assertThrows(UsernameAlreadyInUseException.class, () -> {
           createUserAction.createUser(USER);
           createUserAction.createUser(USER);
        });
    }
}