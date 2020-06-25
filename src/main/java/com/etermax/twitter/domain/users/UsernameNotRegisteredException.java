package com.etermax.twitter.domain.users;

public class UsernameNotRegisteredException extends RuntimeException {
    public UsernameNotRegisteredException() {
        super("Username not registered");
    }
}
