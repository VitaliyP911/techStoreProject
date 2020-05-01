package com.epam.dao.impl;

import com.epam.dao.CrudDao;
import com.epam.db.ConnectionDB;
import com.epam.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements CrudDao<User> {

    private static final String GET_BY_ID = "SELECT * FROM users WHERE ID = ?;";
    private static final String GET_BY_FIELD = "SELECT * FROM users WHERE email = ?;";
    private static final String GET_ALL = "SELECT * FROM users;";
    private static final String INSERT = "INSERT INTO users (name, surname, email, password)" +
            " VALUES (?, ?, ?, ?);";
    private static final String UPDATE_BY_ID = "UPDATE users SET name = ?, surname =?, email = ?, password = ? WHERE ID = ?";
    private static final String UPDATE_BY_FIELD = "UPDATE users SET name = ?, surname =?, password = ? WHERE email = ?";
    private static final String DELETE_BY_ID = "DELETE FROM users WHERE ID = ?;";

    public static User getUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setName(resultSet.getString("name"));
        user.setSurname(resultSet.getString("surname"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        return user;
    }

    @Override
    public Optional<User> getById(Long id) {
        Optional<User> user = Optional.empty();

        try(Connection connection = ConnectionDB.getConnection()){
            PreparedStatement statement = connection.prepareStatement(GET_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                user = Optional.of(getUser(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public Optional<User> getByField(Object field) {
        Optional<User> user = Optional.empty();

        try(Connection connection = ConnectionDB.getConnection()){
            PreparedStatement statement = connection.prepareStatement(GET_BY_FIELD);
            statement.setObject(1, field);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                user = Optional.of(getUser(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> user = new ArrayList<>();

        try(Connection connection = ConnectionDB.getConnection()){
            PreparedStatement statement = connection.prepareStatement(GET_ALL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                user.add(getUser(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean save(User entity) {
        boolean flag = false;
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT);
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            statement.setString(3, entity.getEmail());
            statement.setString(4, entity.getPassword());
            statement.executeUpdate();
            flag = true;
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean deleteById(Long id) {
        boolean flag = false;
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID);
            statement.setLong(1, id);
            statement.executeUpdate();
            flag = true;
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean update(Long id, User entity) {
        boolean flag = false;
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_BY_ID);
            statement.setLong(5, id);
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getSurname());
            statement.setString(3, entity.getEmail());
            statement.setString(4, entity.getPassword());
            statement.executeUpdate();
            flag = true;
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return flag;
    }
}
