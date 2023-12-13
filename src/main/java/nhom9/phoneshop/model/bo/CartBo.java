package nhom9.phoneshop.model.bo;

import nhom9.phoneshop.model.bean.CartBean;
import nhom9.phoneshop.model.bean.CartItem;
import nhom9.phoneshop.model.dao.CartDao;

import java.util.ArrayList;

public class CartBo{

    public CartBean getCartByCustomer(int CustomerID){
        CartDao dao = new CartDao();
        var cart = dao.getCartsByCustomerID(CustomerID);
        if (cart == null) return null;
        CartBean result = new CartBean(cart.getCartID(), cart.getCustomerID());
        ArrayList<CartItem> items = dao.getCartItems(cart.getCartID());
        result.setItems(items);
        return result;
    }

    public CartBean getCartByID(int CartID){
        CartDao dao = new CartDao();
        var cart = dao.getCartsByID(CartID);
        if (cart == null) return null;
        CartBean result = new CartBean(cart.getCartID(), cart.getCustomerID());
        ArrayList<CartItem> items = dao.getCartItems(cart.getCartID());
        result.setItems(items);
        return result;
    }


}
