package com.epam.dao.impl;

import com.epam.dao.CrudDaoImpl;
import com.epam.entity.Category;
import com.epam.entity.History;

import java.sql.*;
import java.time.format.DateTimeFormatter;


public class HistoryDaoImpl extends CrudDaoImpl<History> {

    private static final String GET_BY_ID = "SELECT * FROM history WHERE ID = ?;";
    private static final String GET_BY_FIELD = "SELECT * FROM history WHERE userID = ?;";
    private static final String GET_ALL = "SELECT * FROM history;";
    private static final String INSERT = "INSERT INTO history (nameProduct, price, nameCompany, count, userID, time, amountDue)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String DELETE_BY_ID = "DELETE FROM history WHERE ID = ?;";
    private static final String DELETE_BY_FIELD = "DELETE FROM history WHERE userID = ?;";

    @Override
    protected History getFields(ResultSet resultSet) {

        History history = new History();

        try {
            history.setId(resultSet.getLong("ID"));
            history.setNameProduct(resultSet.getString("nameProduct"));
            history.setPrice(resultSet.getInt("price"));
            history.setNameCompany(resultSet.getString("nameCompany"));
            history.setCount(resultSet.getInt("count"));
            history.setUserID(resultSet.getLong("userID"));
            history.setTime(resultSet.getTimestamp("time"));
            history.setAmountDue(resultSet.getInt("amountDue"));
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
            statement.setString(3, entity.getNameCompany());
            statement.setInt(4, entity.getCount());
            statement.setLong(5, entity.getUserID());
            statement.setTimestamp(6, entity.getTime());
            statement.setInt(7, entity.getAmountDue());
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
        sqlQuary.put("DELETE_BY_ID", DELETE_BY_ID);
        sqlQuary.put("DELETE_BY_FIELD", DELETE_BY_FIELD);
    }
}
