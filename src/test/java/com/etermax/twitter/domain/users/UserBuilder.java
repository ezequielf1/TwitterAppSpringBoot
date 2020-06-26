package com.etermax.twitter.domain.users;

public class UserBuilder {
    private String username;
    private String realName;
    public static UserBuilder aUser() {
        return new UserBuilder();
    }

    public UserBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder withRealName(String realName) {
        this.realName = realName;
        return this;
    }

    public User build() {
        return new User(username, realName);
    }
}
