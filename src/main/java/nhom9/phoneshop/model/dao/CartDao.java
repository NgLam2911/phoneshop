package nhom9.phoneshop.model.dao;

import nhom9.phoneshop.model.bean.CartItem;
import nhom9.phoneshop.model.bean.ProductBean;
import java.sql.SQLException;
import java.util.ArrayList;

public class CartDao extends BaseDao{
    public ArrayList<CartItem> getCartItems(int CustomerID){
        ArrayList<CartItem> items = new ArrayList<>();
        String sql =
                "SELECT * FROM carts " +
                "INNER JOIN products ON carts.ProductID = products.ProductID " +
                "INNER JOIN manufacturers ON products.ManufacturerID = manufacturers.ManufacturerID " +
                "WHERE CustomerID = ?";
        try{
            this.connect();
            var statement = this.getConnection().prepareStatement(sql);
            statement.setInt(1, CustomerID);
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
                items.add(new CartItem(product, resultSet.getInt("Amount"), resultSet.getBoolean("IsPaid")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.close();
        }
        return items;
    }

    public void addProductToCart(int CustomerID, int ProductID, int Amount){
        String sql = "INSERT INTO carts (CustomerID, ProductID, Amount) VALUES (?, ?, ?)";
        try{
            this.connect();
            var statement = this.getConnection().prepareStatement(sql);
            statement.setInt(1, CustomerID);
            statement.setInt(2, ProductID);
            statement.setInt(3, Amount);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.close();
        }
    }

    public void removeProductFromCart(int CustomerID, int ProductID){
        String sql = "DELETE FROM carts WHERE CustomerID = ? AND ProductID = ?";
        try{
            this.connect();
            var statement = this.getConnection().prepareStatement(sql);
            statement.setInt(1, CustomerID);
            statement.setInt(2, ProductID);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.close();
        }
    }

    public void updateProductAmount(int CustomerID, int ProductID, int Amount){
        String sql = "UPDATE carts SET Amount = ? WHERE CustomerID = ? AND ProductID = ?";
        try{
            this.connect();
            var statement = this.getConnection().prepareStatement(sql);
            statement.setInt(1, Amount);
            statement.setInt(2, CustomerID);
            statement.setInt(3, ProductID);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.close();
        }
    }

    public void clearCart(int CustomerID){
        String sql = "DELETE FROM carts WHERE CustomerID = ?";
        try{
            this.connect();
            var statement = this.getConnection().prepareStatement(sql);
            statement.setInt(1, CustomerID);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.close();
        }
    }
}
