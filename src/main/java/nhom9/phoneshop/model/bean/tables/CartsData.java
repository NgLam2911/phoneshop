package nhom9.phoneshop.model.bean.tables;

public class CartsData {

    private int CartID;
    private int ProductID;
    private int Amount;

    public CartsData(){
    }

    public CartsData(int CartID, int ProductID, int Amount){
        this.CartID = CartID;
        this.ProductID = ProductID;
        this.Amount = Amount;
    }

    public int getCartID(){
        return CartID;
    }

    public int getProductID() {
        return ProductID;
    }

    public int getAmount() {
        return Amount;
    }
}
