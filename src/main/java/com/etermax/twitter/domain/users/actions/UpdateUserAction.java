package com.etermax.twitter.domain.users.actions;

import com.etermax.twitter.domain.users.User;
import com.etermax.twitter.domain.users.repository.UserRepository;
import com.etermax.twitter.domain.users.exceptions.UsernameNotRegisteredException;

public class UpdateUserAction {
    private UserRepository userRepository;

    public UpdateUserAction(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User updateUser(User user) throws UsernameNotRegisteredException {
        if (userRepository.isUserRegistered(user)) {
            return userRepository.update(user);
        }
        throw new UsernameNotRegisteredException();
    }
}
