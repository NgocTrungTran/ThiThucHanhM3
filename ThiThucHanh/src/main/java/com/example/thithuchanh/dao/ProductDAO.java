package com.example.thithuchanh.dao;

import com.example.thithuchanh.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDao {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/products?useSSL=false";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "Trantrung.00";

    private static final String SELECT_ALL_PRODUCTS_SQL = "SELECT * FROM products.product;";
    private static final String INSERT_PRODUCT_SQL = "INSERT INTO product (name, price, quantity, color, descri, category) VALUES (?, ?, ?, ?, ?, ? );";
    private static final String UPDATE_PRODUCT_SQL = "UPDATE `products`.`product` SET `name` = ?, `price` = ?, `quantity` = ?, `color` = ?, `descri` = ?, `category` = ? WHERE (`id` = ?);";
    private static final String DELETE_PRODUCT_SQL = "delete from `products`.`product` where id = ?;";
    private static final String SELECT_PRODUCT_BY_ID = "select * from `products`.`product` where id =?;";

    public ProductDAO() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName ( "com.mysql.jdbc.Driver" );
            connection = DriverManager.getConnection ( jdbcURL, jdbcUsername, jdbcPassword );
        } catch (SQLException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace ();
        }
        return connection;
    }

    @Override
    public List<Product> selectAllProduct() {
        List<Product> products = new ArrayList<> ();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection ();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement ( SELECT_ALL_PRODUCTS_SQL );) {
            System.out.println ( preparedStatement );
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery ();

            // Step 4: Process the ResultSet object.
            while (rs.next ()) {
                int id = rs.getInt ( "id" );
                String name = rs.getString ( "name" );
                int price = rs.getInt ( "price" );
                int quantity = rs.getInt ( "quantity" );
                String color = rs.getString ( "color" );
                String descri = rs.getString ( "descri" );
                String category = rs.getString ( "category" );

                products.add ( new Product ( id, name, price, quantity, color, descri, category ) );
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }
        return products;
    }

    @Override
    public Product selectProduct(int id) {
        Product product = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection ();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement ( SELECT_PRODUCT_BY_ID );) {
            preparedStatement.setInt ( 1, id );
            System.out.println ( preparedStatement );
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery ();

            // Step 4: Process the ResultSet object.
            while (rs.next ()) {
                String name = rs.getString ( "name" );
                int price = rs.getInt ( "price" );
                int quantity = rs.getInt ( "quantity" );
                String color = rs.getString ( "color" );
                String descri = rs.getString ( "descri" );
                String category = rs.getString ( "category" );
                product = new Product ( id, name, price, quantity, color, descri, category );
            }
        } catch (SQLException e) {
            printSQLException ( e );
        }
        return product;
    }

    @Override
    public void insertProduct(Product product) throws SQLException {
        //INSERT INTO product (name, price, quantity, color, descri, category) VALUES (?, ?, ?, ?, ?, ? );";
        try (Connection connection = getConnection (); PreparedStatement preparedStatement = connection.prepareStatement ( INSERT_PRODUCT_SQL )) {
            preparedStatement.setString ( 1, product.getName () );
            preparedStatement.setInt ( 2, product.getPrice () );
            preparedStatement.setInt ( 3, product.getQuantity () );
            preparedStatement.setString ( 4, product.getColor () );
            preparedStatement.setString ( 5, product.getDescribe () );
            preparedStatement.setString ( 6, product.getCategory () );
            System.out.println ( preparedStatement );
            preparedStatement.executeUpdate ();
        } catch (SQLException e) {
            printSQLException ( e );
        }
    }

    @Override
    public boolean editProduct(Product product) throws SQLException {
        boolean rowUpdated;
//        UPDATE `products`.`product` SET `name` = ?, `price` = ?, `quantity` = ?, `color` = ?, `descri` = ?, `category` = ? WHERE (`id` = ?);
        try (Connection connection = getConnection (); PreparedStatement statement = connection.prepareStatement ( UPDATE_PRODUCT_SQL );) {
            statement.setString ( 1, product.getName () );
            statement.setDouble ( 2, product.getPrice () );
            statement.setInt ( 3, product.getQuantity () );
            statement.setString ( 4, product.getColor () );
            statement.setString ( 5, product.getDescribe () );
            statement.setString ( 6, product.getCategory () );
            statement.setInt ( 7, product.getId () );

            rowUpdated = statement.executeUpdate () > 0;
        }
        return rowUpdated;
    }

    @Override
    public boolean deleteProduct(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection (); PreparedStatement statement = connection.prepareStatement ( DELETE_PRODUCT_SQL );) {
            statement.setInt ( 1, id );
            rowDeleted = statement.executeUpdate () > 0;
        }
        return rowDeleted;
    }

    @Override
    public List<Product> searchProduct(String search) throws SQLException {
        Connection connection = getConnection ();
        System.out.println ( "numberpage" );

        String query = "select * from `products`.`product` WHERE name like ?";
        List<Product> productList = new ArrayList<> ();
        PreparedStatement ps = connection.prepareStatement ( query );
        ps.setString ( 1, '%' + search + '%' );

        ResultSet rs = ps.executeQuery ();
        while (rs.next ()) {
            Product product = new Product ();
            product.setId ( rs.getInt ( "id" ) );
            product.setName ( rs.getString ( "name" ) );
            product.setPrice ( rs.getInt ( "price" ) );
            product.setQuantity ( rs.getInt ( "quantity" ) );
            product.setColor ( rs.getString ( "color" ) );
            product.setDescribe ( rs.getString ( "descri" ) );
            product.setCategory ( rs.getString ( "category" ) );

            productList.add ( product );
        }
        return productList;
    }


    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if ( e instanceof SQLException ) {
                e.printStackTrace ( System.err );
                System.err.println ( "SQLState: " + ((SQLException) e).getSQLState () );
                System.err.println ( "Error Code: " + ((SQLException) e).getErrorCode () );
                System.err.println ( "Message: " + e.getMessage () );
                Throwable t = ex.getCause ();
                while (t != null) {
                    System.out.println ( "Cause: " + t );
                    t = t.getCause ();
                }
            }
        }
    }
}
