package com.example.thithuchanh.controller;

import com.example.thithuchanh.dao.IProductDao;
import com.example.thithuchanh.dao.ProductDAO;
import com.example.thithuchanh.model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.function.BiConsumer;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    private IProductDao productDao;
    private String errors = "";

    public void init() {
        productDao = new ProductDAO ();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType ( "text/html;charset=UTF-8" );
        req.setCharacterEncoding ( "utf-8" );
        String action = req.getParameter ( "action" );
        if ( action == null ) {
            action = "";
        }

        try {
            switch (action) {
                case "add":
                    showAddForm ( req, resp );
                    break;
                case "edit":
                    showEditForm(req, resp);
                    break;
                case "delete":
                    deleteProduct ( req, resp );
                    break;
                case "search":
                    searchProduct ( req, resp );
                    break;
                default:
                    listAllProduct ( req, resp );
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException ( ex );
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("utf-8");
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "add":
                    insertProduct(req, resp);
                    break;
                case "edit":
                    updateProduct ( req, resp );
                    break;
                case "search":
//                    listUserSearch(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listAllProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        ProductDAO dao = new ProductDAO ();
        List<Product> listProduct = dao.selectAllProduct();
        request.setAttribute ( "listProduct", listProduct );
        RequestDispatcher view = request.getRequestDispatcher ( "/WEB-INF/product/list.jsp" );
        view.forward ( request, response );
    }
    private void searchProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String search = request.getParameter ( "search" );
        List<Product> productList = productDao.searchProduct ( search );
        request.setAttribute ( "listProduct", productList );
        RequestDispatcher view = request.getRequestDispatcher ( "/WEB-INF/product/list.jsp" );
        view.forward ( request, response );
    }
    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        System.out.println ( "Servlet path: " + this.getServletContext ().getRealPath ( "/" ) );
//        List<Product> listProduct = productDao.selectAllProduct ();
//        request.setAttribute ( "listProduct", listProduct );
        RequestDispatcher dispatcher = request.getRequestDispatcher ( "/WEB-INF/product/add.jsp" );
        dispatcher.forward ( request, response );
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
//        User existingUser = userDAO.selectUser(id);
        Product selectProduct = productDao.selectProduct ( id );
        request.setAttribute("id", selectProduct.getId ());
        request.setAttribute("name", selectProduct.getName ());
        request.setAttribute("price", selectProduct.getPrice ());
        request.setAttribute("quantity", selectProduct.getQuantity ());
        request.setAttribute("color", selectProduct.getColor ());
        request.setAttribute("descri", selectProduct.getDescribe ());
        request.setAttribute("category", selectProduct.getCategory ());


        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/product/edit.jsp");

        dispatcher.forward(request, response);
    }
    private void insertProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
//        String name = request.getParameter ( "name" );
//        int price = Integer.parseInt ( request.getParameter ( "price" ) );
//        int quantity = Integer.parseInt ( request.getParameter ( "quantity" ) );
//        String color = request.getParameter ( "color" );
//        String descri = request.getParameter ( "descri" );
//        String category = request.getParameter ( "category" );
//
//        Product newProduct = new Product ( name, price, quantity, color, descri, category );
//        productDao.insertProduct ( newProduct );
//        RequestDispatcher dispatcher = request.getRequestDispatcher ( "/WEB-INF/product/add.jsp" );
//        dispatcher.forward ( request, response );
//        response.sendRedirect ( "/products" );

        Product product = new Product ();
        boolean flag = true;
        Map<String, String> hashMap = new HashMap<String, String>(); // Luu lai truong nao bi loi va loi gi

        try {

//            user.setId ( Integer.parseInt ( request.getParameter ( "id" ) ) );
            String name = request.getParameter ( "name" );
            product.setName ( name );
            int price = Integer.parseInt ( request.getParameter ( "price" ) );
            product.setPrice ( price );
            int quantity = Integer.parseInt ( request.getParameter ( "quantity" ) );
            product.setQuantity ( quantity );
            String color = request.getParameter ( "color" );
            product.setColor ( color );
            String descri = request.getParameter ( "descri" );
            product.setDescribe ( descri );
            String category = request.getParameter ( "category" );
            product.setCategory ( category );

            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory ();
            Validator validator = validatorFactory.getValidator ();
            Set<ConstraintViolation<Product>> constraintViolations = validator.validate ( product );

            if ( !constraintViolations.isEmpty () ) {

                errors = "<ul style='list-style: none'>";
                // constraintViolations is has error
                for (ConstraintViolation<Product> constraintViolation : constraintViolations) {
                    errors += "<li>" + constraintViolation.getPropertyPath () + " " + constraintViolation.getMessage ()
                            + "</li>";
                }
                errors += "</ul>";


                request.setAttribute ( "product", product );
                request.setAttribute ( "errors", errors );

//                List<Country> listCountry = iCountryDAO.selectAllCountry ();
//                request.setAttribute ( "listCountry", listCountry );

                System.out.println ( this.getClass () + " !constraintViolations.isEmpty()" );
                request.getRequestDispatcher ( "/WEB-INF/product/add.jsp" ).forward ( request, response );
            } else {
                if ( name.length () > 46 ) {
                    flag = false;
                    hashMap.put ( "name", "Ten khong qua 45 ky tu" );
                }
                if ( price <= 1000 || price >= 500000 ) {
                    flag = false;
                    hashMap.put ( "price", "Gia tong khoang tu 1000 den 500.000" );
                }
                if ( quantity <= 1 || quantity >= 100 ) {
                    flag = false;
                    hashMap.put ( "quantity", "So luong trong khoang tu 1 den 100" );
                }

                if ( flag ) {
                    // Create user susscess
                    productDao.insertProduct ( product );


                    Product p = new Product ();
                    request.setAttribute ( "product", p );

                    request.getRequestDispatcher ( "/WEB-INF/product/add.jsp" ).forward ( request, response );
                } else {
                    // Error : Email exits in database
                    // Error: Country invalid
                    errors = "<ul>";
                    // One more field has error
                    hashMap.forEach ( new BiConsumer<String, String> () {
                        @Override
                        public void accept(String keyError, String valueError) {
                            errors += "<li>" + valueError
                                    + "</li>";

                        }
                    } );
                    errors += "</ul>";

                    request.setAttribute ( "product", product );
                    request.setAttribute ( "errors", errors );


                    System.out.println ( this.getClass () + " !constraintViolations.isEmpty()" );
                    request.getRequestDispatcher ( "/WEB-INF/product/add.jsp" ).forward ( request, response );
                }
            }
        }catch (NumberFormatException ex) {
            errors = "<ul  style='list-style: none'>";
            errors += "<li>" + "Input format not right"
                    + "</li>";

            errors += "</ul>";


            request.setAttribute("product", product);
            request.setAttribute("errors", errors);

            request.getRequestDispatcher("/WEB-INF/product/add.jsp").forward(request, response);
        }
        catch(Exception ex){
            ex.printStackTrace ();
        }
    }
    private void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter ( "name" );
        int price = Integer.parseInt ( request.getParameter ( "price" ) );
        int quantity = Integer.parseInt ( request.getParameter ( "quantity" ) );
        String color = request.getParameter ( "color" );
        String descri = request.getParameter ( "descri" );
        String category = request.getParameter ( "category" );

        Product book = new Product (id, name, price, quantity, color, descri, category);
        productDao.editProduct (book);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/products");
//        dispatcher.forward(request, response);
        response.sendRedirect ( "/products" );
    }
    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt ( request.getParameter ( "id" ) );
        productDao.deleteProduct ( id );

        List<Product> listProduct = productDao.selectAllProduct ();
        request.setAttribute ( "listProduct", listProduct );
//        RequestDispatcher dispatcher = request.getRequestDispatcher ( "/products" );
//        dispatcher.forward ( request, response );
        response.sendRedirect ( "/products" );
    }
}
