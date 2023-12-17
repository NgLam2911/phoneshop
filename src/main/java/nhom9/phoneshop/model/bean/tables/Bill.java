package nhom9.phoneshop.model.bean.tables;

import java.sql.Date;

public class Bill {

    private int BillID;
    private int CustomerID;
    private Date PurchaseDate;

    public Bill(int BillID, int CustomerID, Date PurchaseDate) {
        this.BillID = BillID;
        this.CustomerID = CustomerID;
        this.PurchaseDate = PurchaseDate;
    }
    public Bill() {
    }

    public int getBillID() {
        return BillID;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public Date getPurchaseDate() {
        return PurchaseDate;
    }

    public void setBillID(int BillID) {
        this.BillID = BillID;
    }

    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }

    public void setPurchaseDate(Date PurchaseDate) {
        this.PurchaseDate = PurchaseDate;
    }
}
