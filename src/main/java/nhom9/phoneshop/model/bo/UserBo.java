package nhom9.phoneshop.model.bo;

import nhom9.phoneshop.model.bean.CustomerBean;
import nhom9.phoneshop.model.bean.UserBean;
import nhom9.phoneshop.model.dao.RoleDao;
import nhom9.phoneshop.model.dao.UserDao;

import java.util.ArrayList;

public class UserBo {

    public UserBean login(String username, String password) {
        UserDao dao = new UserDao();
        return dao.login(username, password);
    }

    public boolean registerGuest(String customerName, String username, String email, String phoneNumber, String password, String address) {
        UserDao dao = new UserDao();
        if (dao.isExist(username)) {
            return false;
        }
        int roleID = (new RoleDao()).getRoleID("Guest");
        dao.registerCustomer(customerName, username, email, phoneNumber, password, address, roleID);
        return true;
    }

    public boolean registerAdmin(String customerName, String username, String email, String phoneNumber, String password, String address) {
        UserDao dao = new UserDao();
        if (dao.isExist(username)) {
            return false;
        }
        int roleID = (new RoleDao()).getRoleID("Admin");
        dao.registerCustomer(customerName, username, email, phoneNumber, password, address, roleID);
        return true;
    }

    public void updatePassword(String username, String password) {
        UserDao dao = new UserDao();
        dao.updatePassword(username, password);
    }

    public void updateCustomer(String username, String customerName, String email, String phoneNumber, String address) {
        UserDao dao = new UserDao();
        dao.updateCustomer(username, customerName, email, phoneNumber, address);
    }

    public ArrayList<CustomerBean> getAllCustomers() {
        UserDao dao = new UserDao();
        return dao.getAllCustomers();
    }

    public CustomerBean getCustomer(String username) {
        UserDao dao = new UserDao();
        return dao.getCustomer(username);
    }

    public CustomerBean getCustomer(int CustomerID){
        UserDao dao = new UserDao();
        return dao.getCustomer(CustomerID);
    }
}
