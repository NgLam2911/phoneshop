package nhom9.phoneshop.model.bean.tables;

public class Cart {

    private int CartID;
    private int CustomerID;
    
    private int Status;

    public Cart(){
    }

    public Cart(int CartID, int CustomerID, int Status){
        this.CartID = CartID;
        this.CustomerID = CustomerID;
        this.Status = Status;
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

}
