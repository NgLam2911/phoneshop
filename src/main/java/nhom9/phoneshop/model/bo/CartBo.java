package nhom9.phoneshop.model.bo;

import nhom9.phoneshop.model.bean.CartBean;
import nhom9.phoneshop.model.bean.CartItem;
import nhom9.phoneshop.model.dao.CartDao;

import java.util.ArrayList;

public class CartBo{

    public CartBean getLastPendingCartByCustomer(int CustomerID){
        CartDao dao = new CartDao();
        var cart = dao.getLastPendingCartsByCustomerID(CustomerID);
        if (cart == null) return null;
        CartBean result = new CartBean(cart.getCartID(), cart.getCustomerID(), cart.getStatus());
        ArrayList<CartItem> items = dao.getCartItems(cart.getCartID());
        result.setItems(items);
        return result;
    }

    public CartBean getCartByID(int CartID){
        CartDao dao = new CartDao();
        var cart = dao.getCartsByID(CartID);
        if (cart == null) return null;
        CartBean result = new CartBean(cart.getCartID(), cart.getCustomerID(), cart.getStatus());
        ArrayList<CartItem> items = dao.getCartItems(cart.getCartID());
        result.setItems(items);
        return result;
    }

    public boolean buyProduct(int CartID, int ProductID, int Amount){
        //TODO: Buy product
        return true;
    }

    public void removeProduct(int CartID, int ProductID){
        //TODO: Remove product
    }


}
