package com.etermax.twitter.infraestructure;

import com.etermax.twitter.domain.users.User;
import com.etermax.twitter.domain.users.repository.UserRepository;

import java.util.HashMap;

public class UserRepositoryMemory implements UserRepository {
    private HashMap<String, User> users = new HashMap<>();

    @Override
    public User getUserById(String username) {
        return users.get(username);
    }

    @Override
    public boolean isUserRegistered(User user) {
        return users.containsKey(user.getUsername());
    }

    @Override
    public User save(User user) {
        users.put(user.getUsername(), user);
        return user;
    }

    @Override
    public User update(User user) {
        users.replace(user.getUsername(), user);
        return user;
    }

    public HashMap<String, User> getUsers() {
        return users;
    }
}
