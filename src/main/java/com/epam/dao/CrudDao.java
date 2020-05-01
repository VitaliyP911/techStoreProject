package com.epam.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDao<T> {

    Optional<T> getById(Long id);

    Optional<T> getByField(Object field);

    List<T> getAll();

    boolean save(T entity);

    boolean deleteById(Long id);

    boolean update(Long id, T entity);
}
