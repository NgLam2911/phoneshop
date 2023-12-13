package nhom9.phoneshop.model.bean;

import java.util.ArrayList;

public class CartBean {

    public static class CartItem{
        private ProductBean product;
        private int Amount;

        public CartItem(ProductBean product, int Amount){
            this.product = product;
            this.Amount = Amount;
        }

        public ProductBean getProduct(){
            return product;
        }

        public int getAmount(){
            return Amount;
        }
    }
    private int CartID;
    private int CustomerID;

    private ArrayList<CartItem> Items;

    public CartBean(){
        Items = new ArrayList<>();
    }

    public CartBean(int CartID, int CustomerID){
        this.CartID = CartID;
        this.CustomerID = CustomerID;
        Items = new ArrayList<>();
    }

    public CartBean(int CartID, int CustomerID, ArrayList<CartItem> Items){
        this.CartID = CartID;
        this.CustomerID = CustomerID;
        this.Items = Items;
    }

    public int getCartID(){
        return CartID;
    }

    public int getCustomerID(){
        return CustomerID;
    }

    public ArrayList<CartItem> getItems(){
        return Items;
    }

    public void setCartID(int CartID){
        this.CartID = CartID;
    }

    public void setCustomerID(int CustomerID){
        this.CustomerID = CustomerID;
    }

    public void setItems(ArrayList<CartItem> Items){
        this.Items = Items;
    }

    public void addItem(ProductBean product, int Amount){
        Items.add(new CartItem(product, Amount));
    }

    public void removeItem(int index){
        Items.remove(index);
    }
}
