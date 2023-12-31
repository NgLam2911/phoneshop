package nhom9.phoneshop.model.bo;

import nhom9.phoneshop.model.bean.BillBean;
import nhom9.phoneshop.model.bean.CartItem;
import nhom9.phoneshop.model.bean.tables.Bill;
import nhom9.phoneshop.model.dao.BillDao;
import nhom9.phoneshop.model.dao.UserDao;

import java.sql.Date;
import java.util.ArrayList;

public class BillBo {
    public BillBean getBill(int BillID){
        BillBean bill = new BillBean();
        BillDao billDao = new BillDao();
        var billItem = billDao.getBillItem(BillID);
        var billInfo = billDao.getBill(BillID);
        bill.setBillID(BillID);
        bill.setCustomerID(billInfo.getCustomerID());
        bill.setPurchaseDate(billInfo.getPurchaseDate());
        bill.setBillItems(billItem);
        return bill;
    }

    public void addBill(int CustomerID, Date PurchaseDate, ArrayList<CartItem> items){
        BillDao billDao = new BillDao();
        var newBillID = billDao.addBill(CustomerID, PurchaseDate);
        for (CartItem item : items){
            billDao.addBillItem(newBillID, item.getProduct().getProductID(), item.getAmount());
        }
    }

    public void removeBill(int BillID){
        BillDao billDao = new BillDao();
        billDao.deleteBill(BillID);
    }

    public ArrayList<BillBean> getBillsOfCustomer(int CustomerID){
        BillDao billDao = new BillDao();
        var bills = billDao.getBillsOfCustomer(CustomerID);
        ArrayList<BillBean> result = new ArrayList<>();
        for (var bill : bills){
            var billItem = billDao.getBillItem(bill.getBillID());
            result.add(new BillBean(bill.getBillID(), bill.getCustomerID(), bill.getPurchaseDate(), billItem));
        }
        return result;
    }

    public void addBillByUsername(String username, Date PurchaseDate, ArrayList<CartItem> items){
        int CustomerID = (new UserDao()).getCustomer(username).getCustomerID();
        this.addBill(CustomerID, PurchaseDate, items);
    }

    public ArrayList<BillBean> getBillsOfCustomerByUsername(String username){
        int CustomerID = (new UserDao()).getCustomer(username).getCustomerID();
        return this.getBillsOfCustomer(CustomerID);
    }

    public ArrayList<Bill> getAllBill(){
        BillDao billDao = new BillDao();
        return billDao.getAllBill();
    }
}
