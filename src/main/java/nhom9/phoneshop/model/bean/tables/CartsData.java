package nhom9.phoneshop.model.bean.tables;

public class CartsData {

    private int CartID;
    private int ProductID;

    public CartsData(){
    }

    public CartsData(int CartID, int ProductID){
        this.CartID = CartID;
        this.ProductID = ProductID;
    }

    public int getCartID(){
        return CartID;
    }

    public int getProductID() {
        return ProductID;
    }
}
