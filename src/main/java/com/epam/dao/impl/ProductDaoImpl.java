package com.epam.dao.impl;

import com.epam.dao.CrudDaoImpl;
import com.epam.entity.Category;
import com.epam.entity.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDaoImpl  extends CrudDaoImpl<Product>{

    private static final String GET_BY_ID = "SELECT * FROM products WHERE ID = ?;";
    private static final String GET_BY_FIELD = "SELECT * FROM products WHERE name = ?;";
    private static final String GET_ALL = "SELECT * FROM products;";
    private static final String INSERT = "INSERT INTO products (name,name_company, price, guarantee, category)" +
            " VALUES (?, ?, ?, ?, ?);";
    private static final String UPDATE_BY_ID = "UPDATE products SET name = ?, name_company = ?, price = ?, guarantee = ?, category = ? WHERE ID = ?";
    private static final String DELETE_BY_ID = "DELETE FROM products WHERE ID = ?;";

    @Override
    public Product getFields(ResultSet resultSet) {

        Product product = new Product();

        try {
            product.setId(resultSet.getLong("ID"));
            product.setName(resultSet.getString("name"));
            product.setNameCompany(resultSet.getString("name_company"));
            product.setPrice(resultSet.getInt("price"));
            product.setGuarantee(resultSet.getInt("guarantee"));
            product.setCategory(Category.valueOf(resultSet.getString("category")));
        }catch (SQLException e){
            e.printStackTrace();
        }

        return product;
    }

    @Override
    protected PreparedStatement setFields(PreparedStatement statement, Product entity) {

        try {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getNameCompany());
            statement.setInt(3, entity.getPrice());
            statement.setInt(4, entity.getGuarantee());
            statement.setString(5,  entity.getCategory().getNameCategory());
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
