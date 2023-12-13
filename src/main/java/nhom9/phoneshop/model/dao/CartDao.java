package nhom9.phoneshop.model.dao;

import nhom9.phoneshop.model.bean.CartItem;
import nhom9.phoneshop.model.bean.ProductBean;
import nhom9.phoneshop.model.bean.tables.Carts;

import java.sql.SQLException;
import java.util.ArrayList;

public class CartDao extends BaseDao{

    public Carts getCartsByID(int cartID){
        Carts cart = null;
        String sql = "SELECT * FROM carts WHERE CartID = ?";
        try{
            this.connect();
            var statement = this.getConnection().prepareStatement(sql);
            statement.setInt(1, cartID);
            var resultSet = statement.executeQuery();
            if(resultSet.next()){
                cart = new Carts(resultSet.getInt("CartID"), resultSet.getInt("CustomerID"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.close();
        }
        return cart;
    }

    public Carts getCartsByCustomerID(int customerID){
        Carts cart = null;
        String sql = "SELECT * FROM carts WHERE CustomerID = ?";
        try{
            this.connect();
            var statement = this.getConnection().prepareStatement(sql);
            statement.setInt(1, customerID);
            var resultSet = statement.executeQuery();
            if(resultSet.next()){
                cart = new Carts(resultSet.getInt("CartID"), resultSet.getInt("CustomerID"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.close();
        }
        return cart;
    }

    public ArrayList<CartItem> getCartItems(int CartID){
        ArrayList<CartItem> items = new ArrayList<>();
        String sql =
                "SELECT * FROM cartsdata " +
                "INNER JOIN products ON cartsdata.ProductID = products.ProductID " +
                "INNER JOIN manufacturers ON products.ManufacturerID = manufacturers.ManufacturerID " +
                "WHERE CartID = ?";
        try{
            this.connect();
            var statement = this.getConnection().prepareStatement(sql);
            statement.setInt(1, CartID);
            var resultSet = statement.executeQuery();
            while(resultSet.next()){
                ProductBean product = new ProductBean();
                product.setProductID(resultSet.getInt("ProductID"));
                product.setProductName(resultSet.getString("ProductName"));
                product.setPrice(resultSet.getDouble("Price"));
                product.setManufacturerID(resultSet.getInt("ManufacturerID"));
                product.setManufacturerName(resultSet.getString("ManufacturerName"));
                product.setCPU(resultSet.getString("CPU"));
                product.setRAM(resultSet.getString("RAM"));
                product.setDisplaySize(resultSet.getString("DisplaySize"));
                product.setDisplayWidth(resultSet.getInt("DisplayWidth"));
                product.setDisplayHeight(resultSet.getInt("DisplayHeight"));
                product.setOS(resultSet.getString("OS"));
                product.setBattery(resultSet.getString("Battery"));
                product.setCapacity(resultSet.getDouble("Capacity"));
                product.setImage(resultSet.getString("Image"));
                items.add(new CartItem(product, resultSet.getInt("Amount")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.close();
        }
        return items;
    }
}
