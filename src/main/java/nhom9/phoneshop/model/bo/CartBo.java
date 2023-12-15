package nhom9.phoneshop.model.bo;

import nhom9.phoneshop.model.bean.ProductBean;
import nhom9.phoneshop.model.dao.CartDao;
import nhom9.phoneshop.model.dao.MainDao;
import nhom9.phoneshop.model.dao.ProductDao;

import java.sql.SQLException;
import java.util.ArrayList;

public class CartBo{

    public CartBean getLastPendingCartByCustomer(int CustomerID){
        CartDao dao = new CartDao();
        var cart = dao.getLastPendingCartByCustomerID(CustomerID);
        if (cart == null) return null;
        CartBean result = new CartBean(cart.getCartID(), cart.getCustomerID(), cart.getStatus());
        ArrayList<CartItem> items = dao.getCartItems(cart.getCartID());
        result.setItems(items);
        return result;
    }

    public CartBean getCartByID(int CartID){
        CartDao dao = new CartDao();
        var cart = dao.getCartByID(CartID);
        if (cart == null) return null;
        CartBean result = new CartBean(cart.getCartID(), cart.getCustomerID(), cart.getStatus());
        ArrayList<CartItem> items = dao.getCartItems(cart.getCartID());
        result.setItems(items);
        return result;
    }

    public boolean buyProduct(int CartID, int ProductID, int Amount){
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

    public void removeProduct(int CartID, int ProductID){
        (new CartDao()).removeProductFromCart(CartID, ProductID);
    }

    public void paidCart(int CartID){
        (new CartDao()).updateCartStatus(CartID, CartBean.STATUS_PAID);
    }

    public void createCart(int CustomerID){
        (new CartDao()).createCart(CustomerID);
    }
        public ArrayList<CartItem> getCartItems(String username) throws SQLException {
        int customerID = new MainDao().getCustomerID(username);
        return (new CartDao()).getCartItems(customerID);
    }

    public void updateItemFromCart(String username, ArrayList<CartItem> cartItems) {
        (new CartDao()).UpdateItemFromCart(username, cartItems);
    }

    public void removeItemFromCart(String username) {
        (new MainDao()).removeProductFromCart(username);
    }
    
}
