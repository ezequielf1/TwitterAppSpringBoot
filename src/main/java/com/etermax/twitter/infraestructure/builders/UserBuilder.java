package com.etermax.twitter.infraestructure.builders;

import com.etermax.twitter.domain.users.User;
import com.etermax.twitter.infraestructure.Procedures;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class UserBuilder {
    private final Connection connection;

    public UserBuilder(Connection connection) {
        this.connection = connection;
    }

    public User buildFrom(ResultSet resultSet) {
        User user = null;
        try {
            if (resultSet.next()) {
                user = new User(resultSet.getString("USERNAME"), resultSet.getString("REALNAME"));
                user.setFollowings(getFollowingsOf(user));
                user.setFollowers(getFollowersOf(user));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    private HashSet<String> getFollowingsOf(User user) throws SQLException {
        HashSet<String> followings = new HashSet<>();
        ResultSet resultSet = executeGetFollowsQuery(Procedures.GET_FOLLOWINGS, user.getUsername());
        while (resultSet.next()) {
            followings.add(resultSet.getString("USERNAME"));
        }
        return followings;
    }

    private HashSet<String> getFollowersOf(User user) throws SQLException {
        HashSet<String> followers = new HashSet<>();
        ResultSet resultSet = executeGetFollowsQuery(Procedures.GET_FOLLOWERS, user.getUsername());
        while (resultSet.next()) {
            followers.add(resultSet.getString("FOLLOWER_USERNAME"));
        }
        return followers;
    }

    private ResultSet executeGetFollowsQuery(String query, String username) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        return statement.executeQuery();
    }
}
