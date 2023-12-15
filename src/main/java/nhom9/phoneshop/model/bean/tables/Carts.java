package nhom9.phoneshop.model.bean.tables;

public class Carts {

    private int CustomerID;
    private int ProductID;
    private int Amount;

    public Carts(){
    }

    public Carts(int CustomerID, int ProductID, int Amount){
        this.CustomerID = CustomerID;
        this.ProductID = ProductID;
        this.Amount = Amount;
    }

    public int getCartID(){
        return CustomerID;
    }

    public int getProductID() {
        return ProductID;
    }

    public int getAmount() {
        return Amount;
    }
}
