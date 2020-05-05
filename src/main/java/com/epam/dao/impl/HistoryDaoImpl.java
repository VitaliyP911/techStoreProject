package com.epam.dao.impl;

import com.epam.dao.CrudDaoImpl;
import com.epam.entity.Category;
import com.epam.entity.History;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class HistoryDaoImpl extends CrudDaoImpl<History> {

    private static final String GET_BY_ID = "SELECT * FROM history WHERE ID = ?;";
    private static final String GET_BY_FIELD = "SELECT * FROM history WHERE userID = ?;";
    private static final String GET_ALL = "SELECT * FROM history;";
    private static final String INSERT = "INSERT INTO history (nameProduct, price, guarantee, category, userID, date)" +
            " VALUES (?, ?, ?, ?, ?, ?);";
    private static final String UPDATE_BY_ID = "UPDATE history SET nameProduct = ?, price = ?, guarantee = ?, category = ?, userID = ?, date = ? WHERE ID = ?";
    private static final String UPDATE_BY_FIELD = "UPDATE history SET price = ?, guarantee = ?, category = ? WHERE name = ?";
    private static final String DELETE_BY_ID = "DELETE FROM history WHERE ID = ?;";

    @Override
    protected History getFields(ResultSet resultSet) {

        History history = new History();

        try {
            history.setId(resultSet.getLong("ID"));
            history.setNameProduct(resultSet.getString("nameProduct"));
            history.setPrice(resultSet.getInt("price"));
            history.setGuarantee(resultSet.getInt("guarantee"));
            history.setCategory(Category.valueOf(resultSet.getString("category")));
            history.setUserID(resultSet.getLong("userID"));
            history.setDate(resultSet.getTime("date"));
        }catch (SQLException e){
            e.printStackTrace();
        }

        return history;
    }

    @Override
    protected PreparedStatement setFields(PreparedStatement statement, History entity) {
        try {
            statement.setString(1, entity.getNameProduct());
            statement.setInt(2, entity.getPrice());
            statement.setInt(3, entity.getGuarantee());
            statement.setString(4, entity.getCategory().getNameCategory());
            statement.setLong(5, entity.getUserID());
            statement.setDate(5,(Date) entity.getDate());
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
