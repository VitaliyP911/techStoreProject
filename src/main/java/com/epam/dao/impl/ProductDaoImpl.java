package com.epam.dao.impl;

import com.epam.dao.CrudDao;
import com.epam.db.ConnectionDB;
import com.epam.entity.Category;
import com.epam.entity.Product;
import com.epam.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDaoImpl implements CrudDao<Product> {

    private static final String GET_BY_ID = "SELECT * FROM products WHERE ID = ?;";
    private static final String GET_BY_FIELD = "SELECT * FROM products WHERE name = ?;";
    private static final String GET_ALL = "SELECT * FROM products;";
    private static final String INSERT = "INSERT INTO products (name, price, guarantee, category)" +
            " VALUES (?, ?, ?, ?);";
    private static final String UPDATE_BY_ID = "UPDATE products SET name = ?, price = ?, guarantee = ?, category = ? WHERE ID = ?";
    private static final String UPDATE_BY_FIELD = "UPDATE products SET price = ?, guarantee = ?, category = ? WHERE name = ?";
    private static final String DELETE_BY_ID = "DELETE FROM products WHERE ID = ?;";

    public static Product getProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getLong("ID"));
        product.setName(resultSet.getString("name"));
        product.setPrice(resultSet.getBigDecimal("price"));
        product.setGuarantee(resultSet.getBigDecimal("guarantee"));
        product.setCategory(Category.valueOf(resultSet.getString("category")));
        return product;
    }

    @Override
    public Optional<Product> getById(Long id) {
        Optional<Product> product = Optional.empty();
        PreparedStatement statement = null;

        try(Connection connection = ConnectionDB.getConnection()){
            statement = connection.prepareStatement(GET_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                product = Optional.of(getProduct(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return product;
    }

    @Override
    public Optional<Product> getByField(Object field) {
        Optional<Product> product = Optional.empty();
        try(Connection connection = ConnectionDB.getConnection()){
            PreparedStatement statement = connection.prepareStatement(GET_BY_FIELD);
            statement.setObject(1, field);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                product = Optional.of(getProduct(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> getAll() {
        List<Product> product = new ArrayList<>();

        try(Connection connection = ConnectionDB.getConnection()){
            PreparedStatement statement = connection.prepareStatement(GET_ALL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                product.add(getProduct(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return product;
    }

    @Override
    public boolean save(Product entity) {
        boolean flag = false;
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT);
            statement.setString(1, entity.getName());
            statement.setBigDecimal(2, entity.getPrice());
            statement.setBigDecimal(3, entity.getGuarantee());
            statement.setObject(4, entity.getCategory());
            statement.executeUpdate();
            flag = true;
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return flag;
    }


    @Override
    public boolean delete(Long id) {
        boolean flag = false;
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID);
            statement.setLong(1, id);
            statement.executeUpdate();
            flag = true;
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean update(Long id, Product entity) {
        boolean flag = false;
        try(Connection connection = ConnectionDB.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_BY_ID);
            statement.setString(1, entity.getName());
            statement.setBigDecimal(2, entity.getPrice());
            statement.setBigDecimal(3, entity.getGuarantee());
            statement.setObject(4, entity.getCategory());
            statement.executeUpdate();
            flag = true;
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        return flag;
    }

}
