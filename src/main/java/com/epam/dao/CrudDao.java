package com.epam.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDao<TEntity> {

    Optional<TEntity> getById(Long id);

    Optional<TEntity> getByField(String field);

    List<TEntity> getListByField(String field);

    List<TEntity> getAll();

    boolean save(TEntity entity);

    boolean delete(Long id);

    boolean deleteListByFiled(String field);

    boolean update(Long id, TEntity entity);
}
