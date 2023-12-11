package nhom9.phoneshop.model.bo;

import nhom9.phoneshop.model.bean.ProductBean;
import nhom9.phoneshop.model.dao.ProductDao;

import javax.servlet.http.Part;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class ProductBo {

    public ArrayList<ProductBean> getAllProducts() {
        return (new ProductDao()).getAllProducts();
    }

    public boolean registerProduct(String productName, double price, int manufacturerID, String cpu, String ram, String displaySize, int displayWidth, int displayHeight, String os, String battery, double capacity, Part image, Collection<Part> imageContent) throws IOException {
        String fileName = image.getSubmittedFileName();
        String imageLink = "upload/" + fileName;
        for (Part part : imageContent) {
            part.write(imageLink);
        }
        (new ProductDao()).registerProduct(productName, price, manufacturerID, cpu, ram, displaySize, displayWidth, displayHeight, os, battery, capacity, fileName);
        return true;
    }

    public boolean updateProduct(int productID, String productName, double price, int manufacturerID, String cpu, String ram, String displaySize, int displayWidth, int displayHeight, String os, String battery, double capacity, Part image, Collection<Part> imageContent) throws IOException {
        String fileName = image.getSubmittedFileName();
        String imageLink = "upload/" + fileName;
        for (Part part : imageContent) {
            part.write(imageLink);
        }
        (new ProductDao()).updateProduct(productID, productName, price, manufacturerID, cpu, ram, displaySize, displayWidth, displayHeight, os, battery, capacity, fileName);
        return true;
    }

    public boolean deleteProduct(int productID) {
        (new ProductDao()).deleteProduct(productID);
        return true;
    }

    public ProductBean getProduct(int productID) {
        return (new ProductDao()).getProduct(productID);
    }
}
