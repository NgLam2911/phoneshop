package nhom9.phoneshop.model.dao;

import nhom9.phoneshop.model.bean.CartItem;
import nhom9.phoneshop.model.bean.tables.Bill;
import nhom9.phoneshop.model.bean.tables.BillInfo;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class BillDao extends BaseDao{

    public ArrayList<Bill> getBillsOfCustomer(int CustomerID) {
        String sql = "SELECT * FROM bills WHERE CustomerID = ?";
        ArrayList<Bill> bills = new ArrayList<>();
        try{
            this.connect();
            var ps = this.getConnection().prepareStatement(sql);
            ps.setInt(1, CustomerID);
            var rs = ps.executeQuery();
            while (rs.next()){
                int BillID = rs.getInt("BillID");
                int Customer = rs.getInt("CustomerID");
                Date PurchaseDate = rs.getDate("PurchaseDate");
                bills.add(new Bill(BillID, Customer, PurchaseDate));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bills;
    }

    public Bill getBill(int BillID) {
        String sql = "SELECT * FROM bills WHERE BillID = ?";
        Bill bill = null;
        try{
            this.connect();
            var ps = this.getConnection().prepareStatement(sql);
            ps.setInt(1, BillID);
            var rs = ps.executeQuery();
            if (rs.next()){
                int Customer = rs.getInt("CustomerID");
                Date PurchaseDate = rs.getDate("PurchaseDate");
                bill = new Bill(BillID, Customer, PurchaseDate);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bill;
    }

    public Bill getBill(int CustomerID, Date date){
        String sql = "SELECT * FROM bills WHERE CustomerID = ? AND PurchaseDate = ?";
        Bill bill = null;
        try{
            this.connect();
            var ps = this.getConnection().prepareStatement(sql);
            ps.setInt(1, CustomerID);
            ps.setDate(2, date);
            var rs = ps.executeQuery();
            if (rs.next()){
                int BillID = rs.getInt("BillID");
                bill = new Bill(BillID, CustomerID, date);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bill;
    }

    public void addBill(int CustomerID, Date PurchaseDate) {
        String sql = "INSERT INTO bills(CustomerID, PurchaseDate) VALUES (?, ?)";
        try{
            this.connect();
            var ps = this.getConnection().prepareStatement(sql);
            ps.setInt(1, CustomerID);
            ps.setDate(2, PurchaseDate);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteBill(int BillID) {
        String sql = "DELETE FROM bills WHERE BillID = ?";
        try{
            this.connect();
            var ps = this.getConnection().prepareStatement(sql);
            ps.setInt(1, BillID);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateBill(int BillID, int CustomerID, Date PurchaseDate) {
        String sql = "UPDATE bills SET CustomerID = ?, PurchaseDate = ? WHERE BillID = ?";
        try{
            this.connect();
            var ps = this.getConnection().prepareStatement(sql);
            ps.setInt(1, CustomerID);
            ps.setDate(2, PurchaseDate);
            ps.setInt(3, BillID);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<BillInfo> getRawBillItem(int billID){
        String sql = "SELECT * FROM billinfo WHERE BillID = ?";
        ArrayList<BillInfo> billInfos = new ArrayList<>();
        try{
            this.connect();
            var ps = this.getConnection().prepareStatement(sql);
            ps.setInt(1, billID);
            var rs = ps.executeQuery();
            while (rs.next()){
                int BillID = rs.getInt("BillID");
                int ProductID = rs.getInt("ProductID");
                int Amount = rs.getInt("Amount");
                billInfos.add(new BillInfo(BillID, ProductID, Amount));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return billInfos;
    }

    public ArrayList<CartItem> getBillItem(int billID){
        ArrayList<CartItem> billInfos = new ArrayList<>();
        ArrayList<BillInfo> rawBillInfos = this.getRawBillItem(billID);
        for (BillInfo rawBillInfo : rawBillInfos){
            int ProductID = rawBillInfo.getProductID();
            int Amount = rawBillInfo.getAmount();
            ProductDao productDao = new ProductDao();
            billInfos.add(new CartItem(productDao.getProduct(ProductID), Amount));
        }
        return billInfos;
    }

    public void addBillItem(int BillID, int ProductID, int Amount) {
        String sql = "INSERT INTO billinfo(BillID, ProductID, Amount) VALUES (?, ?, ?)";
        try{
            this.connect();
            var ps = this.getConnection().prepareStatement(sql);
            ps.setInt(1, BillID);
            ps.setInt(2, ProductID);
            ps.setInt(3, Amount);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteBillItem(int BillID, int ProductID) {
        String sql = "DELETE FROM billinfo WHERE BillID = ? AND ProductID = ?";
        try{
            this.connect();
            var ps = this.getConnection().prepareStatement(sql);
            ps.setInt(1, BillID);
            ps.setInt(2, ProductID);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateBillItem(int BillID, int ProductID, int Amount) {
        String sql = "UPDATE billinfo SET Amount = ? WHERE BillID = ? AND ProductID = ?";
        try{
            this.connect();
            var ps = this.getConnection().prepareStatement(sql);
            ps.setInt(1, Amount);
            ps.setInt(2, BillID);
            ps.setInt(3, ProductID);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Bill> getAllBill(){
        String sql = "SELECT * FROM bills";
        ArrayList<Bill> bills = new ArrayList<>();
        try{
            this.connect();
            var ps = this.getConnection().prepareStatement(sql);
            var rs = ps.executeQuery();
            while (rs.next()){
                int BillID = rs.getInt("BillID");
                int Customer = rs.getInt("CustomerID");
                Date PurchaseDate = rs.getDate("PurchaseDate");
                bills.add(new Bill(BillID, Customer, PurchaseDate));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bills;
    }
}
