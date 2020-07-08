package com.etermax.twitter.infraestructure.builders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class StatementBuilder {
    private final Connection connection;
    public StatementBuilder(Connection connection) {
        this.connection = connection;
    }

    public PreparedStatement build(String instruction, ArrayList<String> parameters) throws SQLException {
        PreparedStatement statement = createStatementWith(instruction);
        addParameters(statement, parameters);
        return statement;
    }

    private PreparedStatement createStatementWith(String instruction) throws SQLException {
        return connection.prepareStatement(instruction);
    }

    private void addParameters(PreparedStatement statement, ArrayList<String> parameters) throws SQLException {
        for (int i = 1; i<parameters.size() + 1; i++) {
            statement.setString(i, parameters.get(i));
        }
    }
}
