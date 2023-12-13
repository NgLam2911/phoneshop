package nhom9.phoneshop.model.bean;

public class CartBean {

    private int CartID;
    private int CustomerID;
    private int ProductID;
    private int Amount;

    public CartBean(int CartID, int CustomerID, int ProductID, int Amount) {
        this.CartID = CartID;
        this.CustomerID = CustomerID;
        this.ProductID = ProductID;
        this.Amount = Amount;
    }

    public int getCartID() {
        return CartID;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public int getProductID() {
        return ProductID;
    }

    public int getAmount() {
        return Amount;
    }
}
