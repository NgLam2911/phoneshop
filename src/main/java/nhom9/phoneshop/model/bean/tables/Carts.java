package nhom9.phoneshop.model.bean.tables;

public class Carts {

    private int CartID;
    private int CustomerID;

    public Carts(){
    }

    public Carts(int CartID, int CustomerID){
        this.CartID = CartID;
        this.CustomerID = CustomerID;
    }

    public int getCartID(){
        return CartID;
    }

    public int getCustomerID(){
        return CustomerID;
    }

}
