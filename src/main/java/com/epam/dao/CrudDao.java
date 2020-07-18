package com.epam.dao;

import java.util.List;
import java.util.Optional;
/**
 * Interface that contains CRUD methods.
 */
public interface CrudDao<TEntity> {
    /**
     * Method gets object of type TEntity
     * from database.
     *
     * @param id object's id.
     * @return TEntity object
     */
    Optional<TEntity> getById(Long id);

    /**
     * Method gets object of type TEntity
     * from database.
     *
     * @param field
     * @return TEntity object
     */
    Optional<TEntity> getByField(String field);

    /**
     * Method gets list of TEntity object
     * from database.
     *
     * @param field
     * @return list of TEntity object
     */
    List<TEntity> getListByField(String field);

    /**
     * Method gets all TEntity objects
     * with certain params from database.
     *
     * @return list of TEntity object
     */
    List<TEntity> getAll();

    /**
     * Method saves object in database.
     *
     * @param entity object created during registration
     * @return true if object is saved
     */
    boolean save(TEntity entity);

    /**
     * Method deletes object by id in database.
     *
     * @param id object's id.
     * @return true if object is deleted
     */
    boolean delete(Long id);

    /**
     * Method deletes object by field in database.
     *
     * @param field objects with such a field
     * @return true if object is deleted
     */
    boolean deleteListByFiled(String field);

    /**
     * Method updates object by id in database.
     *
     * @param id object's id.
     * @param entity editable object
     * @return true if object is updated
     */
    boolean update(Long id, TEntity entity);
}
