package nhom9.phoneshop.model.bo;

import nhom9.phoneshop.model.bean.ProductBean;
import nhom9.phoneshop.model.bean.tables.Categories;
import nhom9.phoneshop.model.bean.tables.Manufacturers;
import nhom9.phoneshop.model.dao.CategoryDao;
import nhom9.phoneshop.model.dao.ManufacturerDao;
import nhom9.phoneshop.model.dao.ProductDao;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

public class ProductBo {

    public ArrayList<ProductBean> getAllProducts() {
        return (new ProductDao()).getAllProducts();
    }

    public boolean registerProduct(String productName, double price, String manufacturerName, String cpu, String ram, String displaySize, int displayWidth, int displayHeight, String os, String battery, double capacity, Part image, int quantity, String color) throws IOException {
        String fileName = image.getSubmittedFileName();
        InputStream ip = image.getInputStream();
        String imageLink = "upload/" + fileName;
        File imageFile = new File(imageLink);
        imageFile.createNewFile();
        imageFile.setWritable(true);
        imageFile.setReadable(true);
        FileWriter imageWriter = new FileWriter(imageFile);
        imageWriter.write(ip.read());
        imageWriter.close();
        ManufacturerDao manufacturerDao = new ManufacturerDao();
        Manufacturers manufacturer = manufacturerDao.getManufactureByName(manufacturerName);
        if (manufacturer == null) {
            manufacturerDao.registerManufacturer(manufacturerName);
            manufacturer = manufacturerDao.getManufactureByName(manufacturerName);
        }
        int manufacturerID = manufacturer.getManufacturerID();
        (new ProductDao()).registerProduct(productName, price, manufacturerID, cpu, ram, displaySize, displayWidth, displayHeight, os, battery, capacity, imageLink, quantity, color);
        return true;
    }

    public boolean updateProduct(int productID, String productName, double price, String manufacturerName, String cpu, String ram, String displaySize, int displayWidth, int displayHeight, String os, String battery, double capacity, Part image, int quantity, String color) throws IOException {
        String fileName = image.getSubmittedFileName();
        InputStream ip = image.getInputStream();
        String imageLink = "upload/" + fileName;
        File imageFile = new File(imageLink);
        imageFile.createNewFile();
        imageFile.setWritable(true);
        imageFile.setReadable(true);
        FileWriter imageWriter = new FileWriter(imageFile);
        imageWriter.write(ip.read());
        imageWriter.close();
        ManufacturerDao manufacturerDao = new ManufacturerDao();
        Manufacturers manufacturer = manufacturerDao.getManufactureByName(manufacturerName);
        if (manufacturer == null) {
            manufacturerDao.registerManufacturer(manufacturerName);
            manufacturer = manufacturerDao.getManufactureByName(manufacturerName);
        }
        int manufacturerID = manufacturer.getManufacturerID();
        (new ProductDao()).updateProduct(productID, productName, price, manufacturerID, cpu, ram, displaySize, displayWidth, displayHeight, os, battery, capacity, imageLink, quantity, color);
        return true;
    }

    public void deleteProduct(int productID) {
        (new ProductDao()).deleteProduct(productID);
    }

    public ProductBean getProduct(int productID) {
        return (new ProductDao()).getProduct(productID);
    }

    public void addCategory(int ProductID, String categoryName){
        CategoryDao categoryDao = new CategoryDao();
        int categoryID = categoryDao.getCategoryID(categoryName);
        if (categoryID == -1){
            categoryDao.addCategory(categoryName);
            categoryID = categoryDao.getCategoryID(categoryName);
        }
        categoryDao.addCategoryToProduct(ProductID, categoryID);
    }

    public void removeCategory(int ProductID, String categoryName){
        CategoryDao categoryDao = new CategoryDao();
        int categoryID = categoryDao.getCategoryID(categoryName);
        categoryDao.removeCategoryFromProduct(ProductID, categoryID);
    }

    public ArrayList<Categories> getCategories(int ProductID){
        return (new CategoryDao()).getCategoriesOfProduct(ProductID);
    }

    public ArrayList<ProductBean> search(String keyword){
        return (new ProductDao()).searchProduct(keyword);
    }
}
