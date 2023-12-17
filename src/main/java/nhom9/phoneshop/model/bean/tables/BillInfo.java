package nhom9.phoneshop.model.bean.tables;

public class BillInfo {

    private int BillID;
    private int ProductID;
    private int Amount;

    public BillInfo(int BillID, int ProductID, int Amount) {
        this.BillID = BillID;
        this.ProductID = ProductID;
        this.Amount = Amount;
    }

    public BillInfo() {
    }

    public int getBillID() {
        return BillID;
    }

    public int getProductID() {
        return ProductID;
    }

    public int getAmount() {
        return Amount;
    }

    public void setBillID(int BillID) {
        this.BillID = BillID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public void setAmount(int Amount) {
        this.Amount = Amount;
    }
}
