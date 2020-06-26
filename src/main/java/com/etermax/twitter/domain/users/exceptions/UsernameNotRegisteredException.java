package com.etermax.twitter.domain.users.exceptions;

public class UsernameNotRegisteredException extends RuntimeException {
    public UsernameNotRegisteredException() {
        super("Username not registered");
    }
}
