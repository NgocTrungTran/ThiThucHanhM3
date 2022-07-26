package com.example.thithuchanh.dao;

import com.example.thithuchanh.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProductDao {
    List<Product> selectAllProduct();
    Product selectProduct(int id);
    void insertProduct(Product product) throws SQLException;
    boolean editProduct(Product product) throws SQLException;
    boolean deleteProduct(int id) throws SQLException;
    List<Product> searchProduct(String search) throws SQLException;
}
