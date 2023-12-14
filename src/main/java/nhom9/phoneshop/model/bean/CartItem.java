package nhom9.phoneshop.model.bean;

public class CartItem {
    private final ProductBean product;
    private final int Amount;

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
