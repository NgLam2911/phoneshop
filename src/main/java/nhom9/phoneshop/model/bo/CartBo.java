package nhom9.phoneshop.model.bo;

import nhom9.phoneshop.model.bean.CartBean;
import nhom9.phoneshop.model.bean.CartItem;
import nhom9.phoneshop.model.bean.ProductBean;
import nhom9.phoneshop.model.dao.CartDao;
import nhom9.phoneshop.model.dao.ProductDao;
import nhom9.phoneshop.model.dao.UserDao;

import java.util.ArrayList;

public class CartBo{

    public boolean addProduct(int CustomerID, int ProductID, int Amount){
        ProductDao productDao = new ProductDao();
        ProductBean product = productDao.getProduct(ProductID);
        if (product.getQuantity() > Amount){
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

    public CartBean getCart(int CustomerID){
        CartBean cart = new CartBean();
        cart.setCustomerID(CustomerID);
        cart.setItems((new CartDao()).getCartItems(CustomerID));
        return cart;
    }

    public void updateCartItems(int CustomerID, ArrayList<CartItem> items){
        this.clearCart(CustomerID);
        for (CartItem item : items){
            this.addProduct(CustomerID, item.getProduct().getProductID(), item.getAmount());
        }
    }

    public CartBean getCartByUsername(String username){
        int CustomerID = (new UserDao()).getCustomer(username).getCustomerID();
        return this.getCart(CustomerID);
    }

    public boolean addProductByUsername(String username, int ProductID, int Amount){
        int CustomerID = (new UserDao()).getCustomer(username).getCustomerID();
        return this.addProduct(CustomerID, ProductID, Amount);
    }

    public void removeProductByUsername(String username, int ProductID){
        int CustomerID = (new UserDao()).getCustomer(username).getCustomerID();
        this.removeProduct(CustomerID, ProductID);
    }

    public void updateProductAmountByUsername(String username, int ProductID, int Amount){
        int CustomerID = (new UserDao()).getCustomer(username).getCustomerID();
        this.updateProductAmount(CustomerID, ProductID, Amount);
    }

    public void clearCartByUsername(String username){
        int CustomerID = (new UserDao()).getCustomer(username).getCustomerID();
        this.clearCart(CustomerID);
    }

    public void updateCartItemsByUsername(String username, ArrayList<CartItem> items){
        int CustomerID = (new UserDao()).getCustomer(username).getCustomerID();
        this.updateCartItems(CustomerID, items);
    }

    public boolean onPaid(int CustomerID){
        ArrayList<CartItem> items = (new CartDao()).getCartItems(CustomerID);
        // Check if some product is out of stock
        for (CartItem item : items){
            if (item.getProduct().getQuantity() < item.getAmount()){
                return false;
            }
        }
        //Update quantity
        for (CartItem item : items){
            int ProductID = item.getProduct().getProductID();
            int Amount = item.getProduct().getQuantity() - item.getAmount();
            (new ProductDao()).updateQuantity(ProductID, Amount);
        }
        //Add to bill
        (new BillBo()).addBill(CustomerID, new java.sql.Date(System.currentTimeMillis()), items);
        //Clear cart
        this.clearCart(CustomerID);
        return true;
    }

    public boolean onPaid(String username){
        int CustomerID = (new UserDao()).getCustomer(username).getCustomerID();
        return this.onPaid(CustomerID);
    }

}
