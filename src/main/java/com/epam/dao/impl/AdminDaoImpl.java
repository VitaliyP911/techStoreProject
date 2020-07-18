package com.epam.dao.impl;

import com.epam.dao.CrudDaoImpl;
import com.epam.entity.Admin;
import com.epam.entity.Category;
import com.epam.entity.History;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDaoImpl extends CrudDaoImpl<Admin> {

    private static final String GET_BY_ID = "SELECT * FROM admin WHERE ID = ?;";
    private static final String GET_BY_FIELD = "SELECT * FROM admin WHERE email = ?;";
    private static final String GET_ALL = "SELECT * FROM admin;";
    private static final String INSERT = "INSERT INTO admin (email)" +
            " VALUES (?);";
    private static final String DELETE_BY_ID = "DELETE FROM admin WHERE ID = ?;";
    /**
     * Method gets field values ​​and put
     * them in object.
     *
     * @param resultSet
     * @return TEntity object
     */
    @Override
    protected Admin getFields(ResultSet resultSet) {
        Admin admin = new Admin();

        try {
            admin.setId(resultSet.getLong("ID"));
            admin.setEmail(resultSet.getString("email"));
        }catch (SQLException e){
            e.printStackTrace();
        }

        return admin;
    }
    /**
     * Method gets field values from the object and put
     * them in sql request.
     *
     * @param statement
     * @param entity
     * @return PreparedStatement object
     */
    @Override
    protected PreparedStatement setFields(PreparedStatement statement, Admin entity) {
        try {
            statement.setString(1, entity.getEmail());
        }catch (SQLException e){
            e.printStackTrace();
        }
        return statement;
    }
    /**
     * Method initializes required resources.
     */
    @Override
    protected void init() {
        sqlQuary.put("GET_BY_ID", GET_BY_ID);
        sqlQuary.put("GET_BY_FIELD", GET_BY_FIELD);
        sqlQuary.put("GET_ALL", GET_ALL);
        sqlQuary.put("INSERT", INSERT);
        sqlQuary.put("DELETE_BY_ID", DELETE_BY_ID);
    }
}
