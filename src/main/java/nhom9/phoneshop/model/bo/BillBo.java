package nhom9.phoneshop.model.bo;

import nhom9.phoneshop.model.bean.BillBean;
import nhom9.phoneshop.model.bean.CartItem;
import nhom9.phoneshop.model.dao.BillDao;

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
        billDao.addBill(CustomerID, PurchaseDate);
        var billID = billDao.getBill(CustomerID, PurchaseDate).getBillID();
        for (CartItem item : items){
            billDao.addBillItem(billID, item.getProduct().getProductID(), item.getAmount());
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
}
