package com.epam.dao.impl;

import com.epam.dao.CrudDaoImpl;
import com.epam.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl extends CrudDaoImpl<User> {

    private static final String GET_BY_ID = "SELECT * FROM users WHERE ID = ?;";
    private static final String GET_BY_FIELD = "SELECT * FROM users WHERE email = ?;";
    private static final String GET_ALL = "SELECT * FROM users;";
    private static final String INSERT = "INSERT INTO users (name, surname, email, password)" +
            " VALUES (?, ?, ?, ?);";
    private static final String UPDATE_BY_ID = "UPDATE users SET name = ?, surname =?, email = ?, password = ? WHERE ID = ?";
    private static final String UPDATE_BY_FIELD = "UPDATE users SET name = ?, surname =?, password = ? WHERE email = ?";
    private static final String DELETE_BY_ID = "DELETE FROM users WHERE ID = ?;";

    protected User getFields(ResultSet resultSet) {
        User user = new User();
        try {
            user.setId(resultSet.getLong("ID"));
            user.setName(resultSet.getString("name"));
            user.setSurname(resultSet.getString("surname"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    protected PreparedStatement setFields(PreparedStatement statement, User entity) {
        try {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            statement.setString(3, entity.getEmail());
            statement.setString(4, entity.getPassword());
        }catch (SQLException e){
            e.printStackTrace();
        }
        return statement;
    }

    @Override
    protected void init() {
        sqlQuary.put("GET_BY_ID", GET_BY_ID);
        sqlQuary.put("GET_BY_FIELD", GET_BY_FIELD);
        sqlQuary.put("GET_ALL", GET_ALL);
        sqlQuary.put("INSERT", INSERT);
        sqlQuary.put("UPDATE_BY_ID", UPDATE_BY_ID);
        sqlQuary.put("DELETE_BY_ID", DELETE_BY_ID);
    }

}
