package nhom9.phoneshop.model.bo;

import nhom9.phoneshop.model.bean.ProductBean;
import nhom9.phoneshop.model.dao.CartDao;
import nhom9.phoneshop.model.dao.ProductDao;

import java.util.ArrayList;

public class CartBo{

    public boolean addProduct(int CustomerID, int ProductID, int Amount){
        ProductDao productDao = new ProductDao();
        ProductBean product = productDao.getProduct(ProductID);
        if (product.getQuantity() > Amount){
            product.setQuantity(product.getQuantity() - Amount);
            productDao.updateQuantity(product.getProductID(), product.getQuantity());
            (new CartDao()).addProductToCart(CustomerID, ProductID, Amount);
            return true;
        }
        return false;
    }

    public void removeProduct(int CustomerID, int ProductID){
        (new CartDao()).removeProductFromCart(CustomerID, ProductID);
    }

    public void updateProductAmount(int CustomerID, int ProductID, int Amount){
        (new CartDao()).updateProductAmount(CustomerID, ProductID, Amount);
    }

    public void clearCart(int CustomerID){
        (new CartDao()).clearCart(CustomerID);
    }
}
