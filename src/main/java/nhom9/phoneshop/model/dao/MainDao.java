package nhom9.phoneshop.model.dao;

import com.mysql.cj.xdevapi.SqlStatement;
import nhom9.phoneshop.model.bean.tables.*;

import java.sql.*;
import java.util.ArrayList;

public class MainDao extends BaseDao{

    public int getCustomerID(String username) throws SQLException {
        String sql = "SELECT * FROM customers WHERE Username = ?";
        PreparedStatement statement = this.getConnection().prepareStatement(sql);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();
        int customerID = 0;
        if (resultSet.next()){
            customerID = resultSet.getInt("CustomerID");
        }
        return customerID;
    }

    public ArrayList<Cart> getAllCarts() throws SQLException {
        String sql = "SELECT * FROM carts";
        PreparedStatement statement = this.getConnection().prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Cart> carts = new ArrayList<>();
        while (resultSet.next()){
            int cartID = resultSet.getInt("CartID");
            int customerID = resultSet.getInt("CustomerID");
            int status = resultSet.getInt("Status");
            carts.add(new Cart(cartID, customerID, status));
        }
        return carts;
    }

    public ArrayList<CartsData> getAllCartsData() throws SQLException {
        String sql = "SELECT * FROM cartsdata";
        PreparedStatement statement = this.getConnection().prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        ArrayList<CartsData> cartsData = new ArrayList<>();
        while (resultSet.next()){
            int cartID = resultSet.getInt("CartID");
            int productID = resultSet.getInt("ProductID");
            int amount = resultSet.getInt("Amount");
            cartsData.add(new CartsData(cartID, productID, amount));
        }
        return cartsData;
    }

    public ArrayList<Categories> getAllCategories() throws SQLException{
        String sql = "SELECT * FROM categories";
        PreparedStatement statement = this.getConnection().prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Categories> categories = new ArrayList<>();
        while (resultSet.next()){
            int categoryID = resultSet.getInt("CategoryID");
            String categoryName = resultSet.getString("CategoryName");
            categories.add(new Categories(categoryID, categoryName));
        }
        return categories;
    }

    public ArrayList<CategoriesData> getAllCategoriesData() throws Exception {
        String sql = "SELECT * FROM categoriesdata";
        PreparedStatement statement = this.getConnection().prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        ArrayList<CategoriesData> categoriesData = new ArrayList<>();
        while (resultSet.next()) {
            int categoryID = resultSet.getInt("CategoryID");
            int productID = resultSet.getInt("ProductID");
            categoriesData.add(new CategoriesData(categoryID, productID));
        }
        return categoriesData;
    }
    public ArrayList<Cart> getCartItems(int CustomerID) throws SQLException {
        String sql = "SELECT * FROM carts where CustomerID = ?";
        PreparedStatement statement = this.getConnection().prepareStatement(sql);
        statement.setInt(1, CustomerID);
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Cart> carts = new ArrayList<>();
        while (resultSet.next()){
            int cartID = resultSet.getInt("CartID");
            int customerID = resultSet.getInt("CustomerID");
            int status = resultSet.getInt("Status");
            carts.add(new Cart(cartID, customerID, status));
        }
        return carts;
    }

    public ArrayList<CartsData> addProductToCart(String id) {
        
        return null;
    }

    public Object removeProductFromCart(String id) {
        return null;
    }
}
