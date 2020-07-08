package com.etermax.twitter.infraestructure;

public class Procedures {
    public static final String CREATE = "CALL CREATE_USER(?, ?)";
    public static final String GET_USER = "CALL GET_USER(?)";
    public static final String UPDATE_REALNAME = "CALL UPDATE_REALNAME(?, ?)";
    public static final String START_FOLLOW = "CALL START_FOLLOW(?, ?)";
    public static final String GET_FOLLOWINGS = "CALL GET_FOLLOWINGS(?)";
    public static final String GET_FOLLOWERS = "CALL GET_FOLLOWERS(?)";
}
