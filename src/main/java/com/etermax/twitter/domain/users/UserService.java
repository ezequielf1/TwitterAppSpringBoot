package com.etermax.twitter.domain.users;

import com.etermax.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User newUser) throws UsernameAlreadyInUseException {
        if (isUserRegistered(newUser)) {
            throw new UsernameAlreadyInUseException();
        }
        return userRepository.save(newUser);
    }

    public User updateUser(User user) throws UsernameNotRegisteredException {
        if (isUserRegistered(user)) {
            return updateUserRealName(user);
        }
        throw new UsernameNotRegisteredException();
    }

    private boolean isUserRegistered(User user) {
        return userRepository.existsById(user.getUsername());
    }

    private User updateUserRealName(User user) {
        User userToUpdate = userRepository.getOne(user.getUsername());
        userToUpdate.setRealName(user.getRealName());
        return userRepository.save(userToUpdate);
    }
}
