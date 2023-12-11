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
                products.add(new ProductBean(productID, productName, price, manufacturerID, manufacturerName, cpu, ram, displaySize, displayWidth, displayHeight, os, battery, capacity, image));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.close();
        }
        return products;
    }

    public boolean registerProduct(){

    }
}
