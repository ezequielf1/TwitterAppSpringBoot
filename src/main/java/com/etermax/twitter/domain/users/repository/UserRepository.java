package com.etermax.twitter.domain.users.repository;

import com.etermax.twitter.domain.users.User;

public interface UserRepository {
    User getUserById(String username);
    boolean isUserRegistered(User user);
    User save(User user);
    User update(User user);
}
