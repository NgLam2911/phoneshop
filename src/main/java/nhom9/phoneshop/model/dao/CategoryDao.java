package nhom9.phoneshop.model.dao;

import nhom9.phoneshop.model.bean.tables.Categories;

import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDao extends BaseDao{

    public void addCategory(String categoryName){
        String sql = "INSERT INTO categories(categoryName) VALUES(?)";
        try{
            var ps = this.getConnection().prepareStatement(sql);
            ps.setString(1, categoryName);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCategory(int categoryID, String categoryName){
        String sql = "UPDATE categories SET categoryName = ? WHERE categoryID = ?";
        try{
            var ps = this.getConnection().prepareStatement(sql);
            ps.setString(1, categoryName);
            ps.setInt(2, categoryID);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCategory(int categoryID){
        String sql = "DELETE FROM categories WHERE categoryID = ?";
        try{
            var ps = this.getConnection().prepareStatement(sql);
            ps.setInt(1, categoryID);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getCategoryID(String categoryName){
        String sql = "SELECT categoryID FROM categories WHERE categoryName = ?";
        try{
            var ps = this.getConnection().prepareStatement(sql);
            ps.setString(1, categoryName);
            var rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt("categoryID");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    public String getCategoryName(int categoryID){
        String sql = "SELECT categoryName FROM categories WHERE categoryID = ?";
        try{
            var ps = this.getConnection().prepareStatement(sql);
            ps.setInt(1, categoryID);
            var rs = ps.executeQuery();
            if(rs.next()){
                return rs.getString("categoryName");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public ArrayList<Categories> getCategoryList(){
        String sql = "SELECT * FROM categories";
        try{
            var ps = this.getConnection().prepareStatement(sql);
            var rs = ps.executeQuery();
            var categoryList = new ArrayList<Categories>();
            while(rs.next()){
                var category = new Categories(
                        rs.getInt("CategoryID"),
                        rs.getString("CategoryName")
                );
                categoryList.add(category);
            }
            return categoryList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Categories> getCategoriesOfProduct(int productID){
        String sql = "SELECT * FROM categories WHERE categoryID IN (SELECT categoryID FROM categoriesdata WHERE productID = ?)";
        try{
            var ps = this.getConnection().prepareStatement(sql);
            ps.setInt(1, productID);
            var rs = ps.executeQuery();
            var categoryList = new ArrayList<Categories>();
            while(rs.next()){
                var category = new Categories(
                        rs.getInt("CategoryID"),
                        rs.getString("CategoryName")
                );
                categoryList.add(category);
            }
            return categoryList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addCategoryToProduct(int categoryID, int productID){
        String sql = "INSERT INTO categoriesdata(categoryID, productID) VALUES(?, ?)";
        try{
            var ps = this.getConnection().prepareStatement(sql);
            ps.setInt(1, categoryID);
            ps.setInt(2, productID);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeCategoryFromProduct(int categoryID, int productID){
        String sql = "DELETE FROM categoriesdata WHERE categoryID = ? AND productID = ?";
        try{
            var ps = this.getConnection().prepareStatement(sql);
            ps.setInt(1, categoryID);
            ps.setInt(2, productID);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
