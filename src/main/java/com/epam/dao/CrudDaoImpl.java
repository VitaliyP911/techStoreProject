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

/**
 * Abstract class that contains CRUD methods.
 */
public abstract class CrudDaoImpl<TEntity> implements CrudDao<TEntity> {

    protected final Map<String, String> sqlQuary;

    /**
     * Default constructor.
     */
    protected CrudDaoImpl() {
        sqlQuary = new HashMap<>();
        init();
    }
    /**
     * Method gets object of type TEntity
     * from database.
     *
     * @param id object's id.
     * @return TEntity object
     */
    @Override
    public Optional<TEntity> getById(Long id) {

        Optional<TEntity> entityOptional = Optional.empty();

        try (Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlQuary.get("GET_BY_ID"));
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                entityOptional = Optional.of(getFields(resultSet));
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            return Optional.empty();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return entityOptional;
    }
    /**
     * Method gets object of type TEntity
     * from database.
     *
     * @param field
     * @return TEntity object
     */
    @Override
    public Optional<TEntity> getByField(String field) {

        Optional<TEntity> entityOptional = Optional.empty();

        try (Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlQuary.get("GET_BY_FIELD"));
            statement.setObject(1, field);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                entityOptional = Optional.of(getFields(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return entityOptional;
    }
    /**
     * Method gets all TEntity objects
     * with certain params from database.
     *
     * @return list of TEntity object
     */
    @Override
    public List<TEntity> getAll() {

        List<TEntity> entities = new ArrayList<>();

        try (Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlQuary.get("GET_ALL"));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                entities.add(getFields(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return entities;
    }
    /**
     * Method saves object in database.
     *
     * @param entity object created during registration
     * @return true if object is saved
     */
    @Override
    public boolean save(TEntity entity) {
        boolean flag = false;
        PreparedStatement statement = null;
        try (Connection connection = ConnectionDB.getConnection()) {
            statement = connection.prepareStatement(sqlQuary.get("INSERT"));
            statement = setFields(statement, entity);
            statement.executeUpdate();
            flag = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new NotSaveException("Not save");
        }
        return flag;
    }
    /**
     * Method deletes object by id in database.
     *
     * @param id object's id.
     * @return true if object is deleted
     */
    @Override
    public boolean delete(Long id) {
        boolean flag = false;
        try (Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlQuary.get("DELETE_BY_ID"));
            statement.setLong(1, id);
            statement.executeUpdate();
            flag = true;
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return flag;
    }
    /**
     * Method updates object by id in database.
     *
     * @param id object's id.
     * @param entity editable object
     * @return true if object is updated
     */
    @Override
    public boolean update(Long id, TEntity entity) {
        boolean flag = false;
        PreparedStatement statement = null;
        try (Connection connection = ConnectionDB.getConnection()) {
            statement = connection.prepareStatement(sqlQuary.get("UPDATE_BY_ID"));
            int size = (int) sqlQuary.get("UPDATE_BY_ID")
                    .chars()
                    .filter(i -> i == '?')
                    .count();
            statement = setFields(statement, entity);
            ;
            statement.setLong(size, id);
            statement.executeUpdate();
            flag = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new NotUpdateException("Not Update");
        }
        return flag;
    }
    /**
     * Method gets list of TEntity object
     * from database.
     *
     * @param field
     * @return list of TEntity object
     */
    @Override
    public List<TEntity> getListByField(String field) {
        List<TEntity> entities = new ArrayList<>();

        try (Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlQuary.get("GET_BY_FIELD"));
            statement.setObject(1, field);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                entities.add(getFields(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return entities;
    }
    /**
     * Method deletes object by field in database.
     *
     * @param field objects with such a field
     * @return true if object is deleted
     */
    @Override
    public boolean deleteListByFiled(String field) {
        boolean flag = false;
        try (Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sqlQuary.get("DELETE_BY_FIELD"));
            statement.setString(1, field);
            statement.executeUpdate();
            flag = true;
        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return flag;
    }

    /**
     * Method gets field values ​​and put
     * them in object.
     *
     * @param resultSet
     * @return TEntity object
     */
    protected abstract TEntity getFields(ResultSet resultSet);

    /**
     * Method gets field values from the object and put
     * them in sql request.
     *
     * @param statement
     * @param entity
     * @return PreparedStatement object
     */
    protected abstract PreparedStatement setFields(PreparedStatement statement, TEntity entity);

    /**
     * Method initializes required resources.
     */
    protected abstract void init();


}
