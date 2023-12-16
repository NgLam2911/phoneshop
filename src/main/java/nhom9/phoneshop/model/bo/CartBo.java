package nhom9.phoneshop.model.bo;

import nhom9.phoneshop.model.bean.CartItem;
import nhom9.phoneshop.model.bean.ProductBean;
import nhom9.phoneshop.model.dao.CartDao;
import nhom9.phoneshop.model.dao.MainDao;
import nhom9.phoneshop.model.dao.ProductDao;

import java.sql.SQLException;
import java.util.ArrayList;

public class CartBo{

    public boolean buyProduct(int CustomerID, int ProductID, int Amount){
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
        public ArrayList<CartItem> getCartItems(String username) throws SQLException {
        int customerID = new MainDao().getCustomerID(username);
        return (new CartDao()).getCartItems(customerID);
    }

    // public void updateItemFromCart(String username, ArrayList<CartItem> cartItems) {
    //     (new CartDao()).UpdateItemFromCart(username, cartItems);
    // }

    public void removeItemFromCart(String username) {
        (new MainDao()).removeProductFromCart(username);
    }
    
}
