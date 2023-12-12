package nhom9.phoneshop.model.dao;

import nhom9.phoneshop.model.bean.tables.Manufacturers;

import java.sql.SQLException;
import java.util.ArrayList;

public class ManufacturerDao extends BaseDao{

    public ArrayList<Manufacturers> getAllManufacturers(){
        ArrayList<Manufacturers> manufacturers = new ArrayList<>();
        String sql = "SELECT * FROM manufacturers";
        try{
            this.connect();
            var statement = this.getConnection().prepareStatement(sql);
            var resultSet = statement.executeQuery();
            while(resultSet.next()){
                manufacturers.add(new Manufacturers(resultSet.getInt("ManufacturerID"), resultSet.getString("ManufacturerName")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.close();
        }
        return manufacturers;
    }

    public Manufacturers getManufactureByName(String name){
        Manufacturers manufacturer = null;
        String sql = "SELECT * FROM manufacturers WHERE ManufacturerName = ?";
        try{
            this.connect();
            var statement = this.getConnection().prepareStatement(sql);
            statement.setString(1, name);
            var resultSet = statement.executeQuery();
            if(resultSet.next()){
                manufacturer = new Manufacturers(resultSet.getInt("ManufacturerID"), resultSet.getString("ManufacturerName"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.close();
        }
        return manufacturer;
    }

    public Manufacturers getManufacturer(int manufacturerID){
        Manufacturers manufacturer = null;
        String sql = "SELECT * FROM manufacturers WHERE ManufacturerID = ?";
        try{
            this.connect();
            var statement = this.getConnection().prepareStatement(sql);
            statement.setInt(1, manufacturerID);
            var resultSet = statement.executeQuery();
            if(resultSet.next()){
                manufacturer = new Manufacturers(resultSet.getInt("ManufacturerID"), resultSet.getString("ManufacturerName"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.close();
        }
        return manufacturer;
    }

    public void registerManufacturer(String manufacturerName){
        String sql = "INSERT INTO manufacturers(ManufacturerName) VALUES (?)";
        try{
            this.connect();
            var statement = this.getConnection().prepareStatement(sql);
            statement.setString(1, manufacturerName);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.close();
        }
    }
}
