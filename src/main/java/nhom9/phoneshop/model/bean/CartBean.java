package nhom9.phoneshop.model.bean;

import java.util.ArrayList;

public class CartBean {
    private int CustomerID;
    private ArrayList<CartItem> Items;

    public CartBean(){
        Items = new ArrayList<>();
    }

    public CartBean(int CustomerID){
        this.CustomerID = CustomerID;
        Items = new ArrayList<>();
    }

    public CartBean(int CustomerID, int Status, ArrayList<CartItem> Items){
        this.CustomerID = CustomerID;
        this.Items = Items;
    }

    public int getCustomerID(){
        return CustomerID;
    }

    public ArrayList<CartItem> getItems(){
        return Items;
    }

    public void setCustomerID(int CustomerID){
        this.CustomerID = CustomerID;
    }

    public void setItems(ArrayList<CartItem> Items){
        this.Items = Items;
    }

    public void addItem(ProductBean product, int Amount, boolean IsPaid){
        Items.add(new CartItem(product, Amount, IsPaid));
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
