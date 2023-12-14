package nhom9.phoneshop.model.bo;

import nhom9.phoneshop.model.bean.UserBean;
import nhom9.phoneshop.model.dao.RoleDao;
import nhom9.phoneshop.model.dao.UserDao;

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


}
