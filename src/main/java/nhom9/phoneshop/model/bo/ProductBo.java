package nhom9.phoneshop.model.bo;

import nhom9.phoneshop.model.bean.ProductBean;
import nhom9.phoneshop.model.bean.tables.Manufacturers;
import nhom9.phoneshop.model.dao.CategoryDao;
import nhom9.phoneshop.model.dao.ManufacturerDao;
import nhom9.phoneshop.model.dao.ProductDao;

import javax.servlet.http.Part;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class ProductBo {

    public ArrayList<ProductBean> getAllProducts() {
        return (new ProductDao()).getAllProducts();
    }

    public boolean registerProduct(String productName, double price, String manufacturerName, String cpu, String ram, String displaySize, int displayWidth, int displayHeight, String os, String battery, double capacity, Part image, Collection<Part> imageContent, int quantity, String color) throws IOException {
        String fileName = image.getSubmittedFileName();
        String imageLink = "upload/" + fileName;
        for (Part part : imageContent) {
            part.write(imageLink);
        }
        ManufacturerDao manufacturerDao = new ManufacturerDao();
        Manufacturers manufacturer = manufacturerDao.getManufactureByName(manufacturerName);
        if (manufacturer == null) {
            manufacturerDao.registerManufacturer(manufacturerName);
            manufacturer = manufacturerDao.getManufactureByName(manufacturerName);
        }
        int manufacturerID = manufacturer.getManufacturerID();
        (new ProductDao()).registerProduct(productName, price, manufacturerID, cpu, ram, displaySize, displayWidth, displayHeight, os, battery, capacity, fileName, quantity, color);
        return true;
    }

    public boolean updateProduct(int productID, String productName, double price, int manufacturerID, String cpu, String ram, String displaySize, int displayWidth, int displayHeight, String os, String battery, double capacity, Part image, Collection<Part> imageContent, int quantity, String color) throws IOException {
        String fileName = image.getSubmittedFileName();
        String imageLink = "upload/" + fileName;
        for (Part part : imageContent) {
            part.write(imageLink);
        }
        (new ProductDao()).updateProduct(productID, productName, price, manufacturerID, cpu, ram, displaySize, displayWidth, displayHeight, os, battery, capacity, fileName, quantity, color);
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
}
