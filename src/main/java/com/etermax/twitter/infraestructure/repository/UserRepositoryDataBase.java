package com.etermax.twitter.infraestructure.repository;

import com.etermax.twitter.domain.users.User;
import com.etermax.twitter.domain.users.repository.UserRepository;
import com.etermax.twitter.infraestructure.Procedures;
import com.etermax.twitter.infraestructure.builders.StatementBuilder;
import com.etermax.twitter.infraestructure.builders.UserBuilder;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public final class UserRepositoryDataBase implements UserRepository {
    private Connection connection;
    private final String databaseUrl = "jdbc:mysql://localhost:8889/TwitterApp";
    private final String databaseUsername = "twitterapp";
    private final String databasePassword = "kelo456";
    private UserBuilder userBuilder;
    private StatementBuilder statementBuilder;

    public UserRepositoryDataBase() {
        try {
            connection = DriverManager.getConnection(databaseUrl,
                                                    databaseUsername,
                                                    databasePassword);
            userBuilder = new UserBuilder(connection);
            statementBuilder = new StatementBuilder(connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public User getUserById(String username) {
        ResultSet resultSet = getUserFromDatabase(username);
        return userBuilder.buildFrom(resultSet);
    }

    @Override
    public boolean isUserRegistered(User user) {
        return isUserRegisteredInDataBase(user);
    }

    @Override
    public User save(User user) {
        createUserInDataBase(user);
        return getUserById(user.getUsername());
    }

    @Override
    public User update(User user) {
        updateUserInDataBase(user);
        return getUserById(user.getUsername());
    }

    @Override
    public User startFollow(String followerUsername, String followedUsername) {
        startFollowInDataBase(followerUsername, followedUsername);
        return getUserById(followerUsername);
    }

    private void startFollowInDataBase(String followerUsername, String followedUsername) {
        final ArrayList<String> parameters = new ArrayList<>(Arrays.asList(followerUsername, followedUsername));
        try {
            statementBuilder.build(Procedures.START_FOLLOW, parameters).executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void createUserInDataBase(User user) {
        final ArrayList<String> parameters = new ArrayList<>(Arrays.asList(user.getUsername(), user.getRealName()));
        try {
            statementBuilder.build(Procedures.CREATE, parameters).executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void updateUserInDataBase(User user) {
        final ArrayList<String> parameters = new ArrayList<>(Arrays.asList(user.getUsername(), user.getRealName()));
        try {
            statementBuilder.build(Procedures.UPDATE_REALNAME, parameters).executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private boolean isUserRegisteredInDataBase(User user) {
        try {
            return getUserFromDatabase(user.getUsername()).next();
        } catch (SQLException throwables) {
            return false;
        }
    }

    private ResultSet getUserFromDatabase(String username) {
        final ArrayList<String> parameters = new ArrayList<>(Arrays.asList(username));
        try {
            return statementBuilder.build(Procedures.GET_USER, parameters).executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}