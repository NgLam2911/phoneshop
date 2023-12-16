package nhom9.phoneshop.model.bean;

public class CartItem {
    private ProductBean product;
    private int Amount;
    //private boolean IsPaid;

    public CartItem(ProductBean product, int Amount){//, boolean IsPaid){
        this.product = product;
        this.Amount = Amount;
        //this.IsPaid = IsPaid;
    }

    public ProductBean getProduct(){
        return product;
    }

    public int getAmount(){
        return Amount;
    }
}
