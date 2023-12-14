package nhom9.phoneshop.model.bean;

import java.util.ArrayList;

public class CartBean {
    //Constants
    public static int STATUS_PENDING = 0;
    public static int STATUS_PAID = 1;

    private int CartID;
    private int CustomerID;

    private int Status;

    private ArrayList<CartItem> Items;

    public CartBean(){
        Items = new ArrayList<>();
    }

    public CartBean(int CartID, int CustomerID, int Status){
        this.CartID = CartID;
        this.CustomerID = CustomerID;
        Items = new ArrayList<>();
    }

    public CartBean(int CartID, int CustomerID, int Status, ArrayList<CartItem> Items){
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

    public int getStatus(){
        return Status;
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

    public void setStatus(int Status){
        this.Status = Status;
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

    public double getTotalPrice(){
        double result = 0;
        for (CartItem item : Items){
            result += item.getProduct().getPrice() * item.getAmount();
        }
        return result;
    }
}
