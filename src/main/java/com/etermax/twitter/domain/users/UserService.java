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
        if (userRepository.existsById(newUser.getUsername())) {
            throw new UsernameAlreadyInUseException();
        }
        return userRepository.save(newUser);
    }
}
