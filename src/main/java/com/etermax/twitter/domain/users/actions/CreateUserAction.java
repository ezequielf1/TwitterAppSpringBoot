package com.etermax.twitter.domain.users.actions;

import com.etermax.twitter.domain.users.User;
import com.etermax.twitter.domain.users.repository.UserRepository;
import com.etermax.twitter.domain.users.exceptions.UsernameAlreadyInUseException;

public class CreateUserAction {
    private UserRepository userRepository;

    public CreateUserAction(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User newUser) throws UsernameAlreadyInUseException {
        if (userRepository.isUserRegistered(newUser)) {
            throw new UsernameAlreadyInUseException();
        }
        return userRepository.save(newUser);
    }
}
