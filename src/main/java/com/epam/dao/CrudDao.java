package com.epam.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudDao<T> {

    Optional<T> getById(Long id) throws SQLException;

    Optional<T> getByField(Object field) throws SQLException;

    List<T> getAll();

    boolean save(T entity);

    boolean delete(Long id);

    boolean update(Long id, T entity);
}
