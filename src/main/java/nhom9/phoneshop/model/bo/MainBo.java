package nhom9.phoneshop.model.bo;

import nhom9.phoneshop.model.bean.ProductBean;
import nhom9.phoneshop.model.bean.tables.*;
import nhom9.phoneshop.model.dao.MainDao;
import nhom9.phoneshop.model.dao.ProductDao;

import java.sql.SQLException;
import java.util.ArrayList;

public class MainBo {

    public MainBo() {
        // Does nothing rn, i need some info from the view to make this work
    }

    // Controller -> Method -> Bo -> Dao -> Database
    // Describe what the Controller need to do
    public ArrayList<Carts> getAllCarts() throws SQLException {
        return (new MainDao()).getAllCarts();
    }

    public ArrayList<CartsData> getAllCartsData() throws SQLException {
        return (new MainDao()).getAllCartsData();
    }

    public ArrayList<Categories> getAllCategories() throws SQLException {
        return (new MainDao()).getAllCategories();
    }

    public ArrayList<CategoriesData> getAllCategoriesData() throws Exception {
        return (new MainDao()).getAllCategoriesData();
    }

    public ArrayList<CartsData> getCartProducts() throws SQLException {
        return (new MainDao()).getAllCartsData();
    }

    public void updateProductFromCart(String username) {
        (new MainDao()).addProductToCart(username);
    }

    public void removeProductFromCart(String username) {
        (new MainDao()).removeProductFromCart(username);
    }

    // This is some base methods

}
