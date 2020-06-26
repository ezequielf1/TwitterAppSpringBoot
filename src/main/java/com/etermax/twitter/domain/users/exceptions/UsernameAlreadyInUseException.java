package com.etermax.twitter.domain.users.exceptions;

public class UsernameAlreadyInUseException extends RuntimeException {
    public UsernameAlreadyInUseException() {
        super("Username already in use");
    }
}
