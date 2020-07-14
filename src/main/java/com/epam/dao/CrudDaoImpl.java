package com.epam.dao;

import com.epam.db.ConnectionDB;
import com.epam.entity.User;
import com.epam.exception.NotSaveException;
import com.epam.exception.NotUpdateException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public abstract class CrudDaoImpl<TEntity> implements CrudDao<TEntity> {

    protected final Map<String,String> sqlQuary;

    protected CrudDaoImpl() {
        sqlQuary = new HashMap<>();
        init();
    }

    @Override
    public Optional<TEntity> getById(Long id) {

        Optional<TEntity> entityOptional = Optional.empty();

        try(Connection connection = ConnectionDB.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sqlQuary.get("GET_BY_ID"));
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                entityOptional = Optional.of(getFields(resultSet));
            }
        } catch (NullPointerException e){
            e.printStackTrace();
            return Optional.empty();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return entityOptional;
    }

    @Override
    public Optional<TEntity> getByField(String field) {

        Optional<TEntity> entityOptional = Optional.empty();

        try(Connection connection = ConnectionDB.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sqlQuary.get("GET_BY_FIELD"));
            statement.setObject(1, field);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                entityOptional = Optional.of(getFields(resultSet));
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return entityOptional;
    }

    @Override
    public List<TEntity> getAll() {

        List<TEntity> entities = new ArrayList<>();

        try(Connection connection = ConnectionDB.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sqlQuary.get("GET_ALL"));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                entities.add(getFields(resultSet));
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return entities;
    }

    @Override
    public boolean save(TEntity entity) {
        boolean flag = false;
        PreparedStatement statement = null;
        try(Connection connection = ConnectionDB.getConnection()) {
            statement = connection.prepareStatement(sqlQuary.get("INSERT"));
            statement = setFields(statement, entity);
            statement.executeUpdate();
            flag = true;
        }catch(SQLException ex) {
            ex.printStackTrace();
            throw new NotSaveException("Not save");
        }
        return flag;
    }

    @Override
    public boolean delete(Long id) {
        boolean flag = false;
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlQuary.get("DELETE_BY_ID"));
            statement.setLong(1, id);
            statement.executeUpdate();
            flag = true;
        }catch(SQLException ex) {
            ex.printStackTrace();

        }
        return flag;
    }

    @Override
    public boolean update(Long id, TEntity entity) {
        boolean flag = false;
        PreparedStatement statement = null;
        try(Connection connection = ConnectionDB.getConnection()) {
            statement = connection.prepareStatement(sqlQuary.get("UPDATE_BY_ID"));
            int size = (int) sqlQuary.get("UPDATE_BY_ID")
                    .chars()
                    .filter(i -> i == '?')
                    .count();
            statement = setFields(statement, entity);;
            statement.setLong(size, id);
            statement.executeUpdate();
            flag = true;
        }catch(SQLException ex) {
            ex.printStackTrace();
            throw new NotUpdateException("Not Update");
        }
        return flag;
    }

    @Override
    public List<TEntity> getListByField(String field) {
        List<TEntity> entities = new ArrayList<>();

        try(Connection connection = ConnectionDB.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sqlQuary.get("GET_BY_FIELD"));
            statement.setObject(1, field);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                entities.add(getFields(resultSet));
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return entities;
    }

    @Override
    public boolean deleteListByFiled(String field) {
        boolean flag = false;
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlQuary.get("DELETE_BY_FIELD"));
            statement.setString(1, field);
            statement.executeUpdate();
            flag = true;
        }catch(SQLException ex) {
            ex.printStackTrace();

        }
        return flag;
    }

    protected abstract TEntity getFields(ResultSet resultSet);

    protected abstract PreparedStatement setFields(PreparedStatement statement, TEntity entity);

    protected abstract void init();


}
