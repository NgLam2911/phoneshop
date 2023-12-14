package nhom9.phoneshop.model.dao;

import nhom9.phoneshop.model.bean.ProductBean;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDao extends BaseDao {

    public ArrayList<ProductBean> getAllProducts(){
        ArrayList<ProductBean> products = new ArrayList<>();
        String sql = "SELECt * FROM products INNER JOIN manufacturers ON products.ManufacturerID = manufacturers.ManufacturerID";
        try{
            this.connect();
            var statement = this.getConnection().prepareStatement(sql);
            var resultSet = statement.executeQuery();
            while (resultSet.next()){
                int productID = resultSet.getInt("ProductID");
                String productName = resultSet.getString("ProductName");
                double price = resultSet.getDouble("Price");
                int manufacturerID = resultSet.getInt("ManufacturerID");
                String manufacturerName = resultSet.getString("ManufacturerName");
                String cpu = resultSet.getString("CPU");
                String ram = resultSet.getString("RAM");
                String displaySize = resultSet.getString("DisplaySize");
                int displayWidth = resultSet.getInt("DisplayWidth");
                int displayHeight = resultSet.getInt("DisplayHeight");
                String os = resultSet.getString("OS");
                String battery = resultSet.getString("Battery");
                double capacity = resultSet.getDouble("Capacity");
                String image = resultSet.getString("Image");
                int quantity = resultSet.getInt("Quantity");
                products.add(new ProductBean(productID, productName, price, manufacturerID, manufacturerName, cpu, ram, displaySize, displayWidth, displayHeight, os, battery, capacity, image, quantity));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.close();
        }
        return products;
    }

    public ProductBean getProduct(int productID){
        ProductBean product = null;
        String sql = "SELECt * FROM products INNER JOIN manufacturers ON products.ManufacturerID = manufacturers.ManufacturerID WHERE ProductID = ?";
        try{
            this.connect();
            var statement = this.getConnection().prepareStatement(sql);
            statement.setInt(1, productID);
            var resultSet = statement.executeQuery();
            if (resultSet.next()){
                String productName = resultSet.getString("ProductName");
                double price = resultSet.getDouble("Price");
                int manufacturerID = resultSet.getInt("ManufacturerID");
                String manufacturerName = resultSet.getString("ManufacturerName");
                String cpu = resultSet.getString("CPU");
                String ram = resultSet.getString("RAM");
                String displaySize = resultSet.getString("DisplaySize");
                int displayWidth = resultSet.getInt("DisplayWidth");
                int displayHeight = resultSet.getInt("DisplayHeight");
                String os = resultSet.getString("OS");
                String battery = resultSet.getString("Battery");
                double capacity = resultSet.getDouble("Capacity");
                String image = resultSet.getString("Image");
                int quantity = resultSet.getInt("Quantity");
                product = new ProductBean(productID, productName, price, manufacturerID, manufacturerName, cpu, ram, displaySize, displayWidth, displayHeight, os, battery, capacity, image, quantity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.close();
        }
        return product;
    }

    public void registerProduct(String productName, double price, int manufacturerID, String cpu, String ram, String displaySize, int displayWidth, int displayHeight, String os, String battery, double capacity, String image, int quantity){
        String sql = "INSERT INTO products(ProductName, Price, ManufacturerID, CPU, RAM, DisplaySize, DisplayWidth, DisplayHeight, OS, Battery, Capacity, Image, Quantity) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            this.connect();
            var statement = this.getConnection().prepareStatement(sql);
            statement.setString(1, productName);
            statement.setDouble(2, price);
            statement.setInt(3, manufacturerID);
            statement.setString(4, cpu);
            statement.setString(5, ram);
            statement.setString(6, displaySize);
            statement.setInt(7, displayWidth);
            statement.setInt(8, displayHeight);
            statement.setString(9, os);
            statement.setString(10, battery);
            statement.setDouble(11, capacity);
            statement.setString(12, image);
            statement.setInt(13, quantity);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.close();
        }
    }

    public void updateProduct(int productID, String productName, double price, int manufacturerID, String cpu, String ram, String displaySize, int displayWidth, int displayHeight, String os, String battery, double capacity, String image, int quantity){
        String sql = "UPDATE products SET ProductName = ?, Price = ?, ManufacturerID = ?, CPU = ?, RAM = ?, DisplaySize = ?, DisplayWidth = ?, DisplayHeight = ?, OS = ?, Battery = ?, Capacity = ?, Image = ?, Quantity = ? WHERE ProductID = ?";
        try{
            this.connect();
            var statement = this.getConnection().prepareStatement(sql);
            statement.setString(1, productName);
            statement.setDouble(2, price);
            statement.setInt(3, manufacturerID);
            statement.setString(4, cpu);
            statement.setString(5, ram);
            statement.setString(6, displaySize);
            statement.setInt(7, displayWidth);
            statement.setInt(8, displayHeight);
            statement.setString(9, os);
            statement.setString(10, battery);
            statement.setDouble(11, capacity);
            statement.setString(12, image);
            statement.setInt(13, quantity);
            statement.setInt(14, productID);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.close();
        }
    }

    public void deleteProduct(int productID){
        String sql = "DELETE FROM products WHERE ProductID = ?";
        try{
            this.connect();
            var statement = this.getConnection().prepareStatement(sql);
            statement.setInt(1, productID);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.close();
        }
    }

    public void updateQuantity(int productID, int quantity){
        String sql = "UPDATE products SET Quantity = ? WHERE ProductID = ?";
        try{
            this.connect();
            var statement = this.getConnection().prepareStatement(sql);
            statement.setInt(1, quantity);
            statement.setInt(2, productID);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.close();
        }
    }

    //TODO: Filter products
}
