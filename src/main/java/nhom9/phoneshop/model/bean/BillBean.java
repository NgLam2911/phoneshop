package nhom9.phoneshop.model.bean;

import java.sql.Date;
import java.util.ArrayList;

public class BillBean {

    private int BillID;
    private int CustomerID;
    private Date PurchaseDate;

    private ArrayList<CartItem> billItems;

    public BillBean(int BillID, int CustomerID, Date PurchaseDate, ArrayList<CartItem> billItems) {
        this.BillID = BillID;
        this.CustomerID = CustomerID;
        this.PurchaseDate = PurchaseDate;
        this.billItems = billItems;
    }
    public BillBean() {
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

    public ArrayList<CartItem> getBillItems() {
        return billItems;
    }

    public void setBillItems(ArrayList<CartItem> billItems) {
        this.billItems = billItems;
    }

    public void addItem(ProductBean product, int Amount, boolean IsPaid){
        billItems.add(new CartItem(product, Amount));//, IsPaid));
    }

    public void removeItem(int index){
        billItems.remove(index);
    }

    public double getBillTotalPrice(){
        double result = 0;
        for (CartItem item : billItems){
            result += item.getProduct().getPrice() * item.getAmount();
        }
        return result;
    }
}
