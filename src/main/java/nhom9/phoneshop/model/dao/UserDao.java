package nhom9.phoneshop.model.dao;

import nhom9.phoneshop.model.bean.CustomerBean;
import nhom9.phoneshop.model.bean.RegisterBean;
import nhom9.phoneshop.model.bean.UserBean;

import java.util.ArrayList;

public class UserDao extends BaseDao{

    public UserBean login(String username, String password) {
        UserBean result = null;
        String sql = "SELECT * FROM users INNER JOIN roles ON users.RoleID = roles.RoleID WHERE Username = ? AND Password = ?";
        try {
            this.connect();
            var statement = this.getConnection().prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            var resultSet = statement.executeQuery();
            if (resultSet.next()){
                result = new UserBean(
                        resultSet.getString("Username"),
                        resultSet.getString("Password"),
                        resultSet.getInt("RoleID"),
                        resultSet.getString("RoleName")
                );
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to db", e);
        } finally {
            this.close();
        }
        return result;
    }

    public boolean isExist(String username) {
        boolean result = false;
        String sql = "SELECT * FROM users WHERE Username = ?";
        try {
            this.connect();
            var statement = this.getConnection().prepareStatement(sql);
            statement.setString(1, username);
            var resultSet = statement.executeQuery();
            if (resultSet.next()){
                result = true;
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to db", e);
        } finally {
            this.close();
        }
        return result;
    }

    public void registerCustomer(String customerName, String username, String email, String phoneNumber, String password, String address, int roleID) {
        String sql1 = "INSERT INTO users (Username, Password, RoleID) VALUES (?, ?, ?)";
        String sql2 = "INSERT INTO customers (CustomerName, Username, Email, Phone, Address) VALUES (?, ?, ?, ?, ?)";
        var connection = getConnection();
        try {
            var statement1 = connection.prepareStatement(sql1);
            var statement2 = connection.prepareStatement(sql2);
            statement1.setString(1, username);
            statement1.setString(2, password);
            statement1.setInt(3, roleID);
            statement1.executeUpdate();

            statement2.setString(1, customerName);
            statement2.setString(2, username);
            statement2.setString(3, email);
            statement2.setString(4, phoneNumber);
            statement2.setString(5, address);
            statement2.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to db", e);
        }
    }

    public void updatePassword(String username, String password){
        String sql = "UPDATE users SET Password = ? WHERE Username = ?";
        try{
            this.connect();
            var statement = this.getConnection().prepareStatement(sql);
            statement.setString(1, password);
            statement.setString(2, username);
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to db", e);
        } finally {
            this.close();
        }
    }

    public void updateCustomer(String customerName, String username, String email, String phoneNumber, String address){
        String sql = "UPDATE customers SET CustomerName = ?, Email = ?, Phone = ?, Address = ? WHERE Username = ?";
        try{
            this.connect();
            var statement = this.getConnection().prepareStatement(sql);
            statement.setString(1, customerName);
            statement.setString(2, email);
            statement.setString(3, phoneNumber);
            statement.setString(4, address);
            statement.setString(5, username);
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to db", e);
        } finally {
            this.close();
        }
    }

    public ArrayList<CustomerBean> getAllCustomers(){
        ArrayList<CustomerBean> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers INNER JOIN users ON customers.Username = users.Username INNER JOIN roles ON users.RoleID = roles.RoleID";
        try{
            this.connect();
            var statement = this.getConnection().prepareStatement(sql);
            var resultSet = statement.executeQuery();
            while(resultSet.next()){
                customers.add(new CustomerBean(
                        resultSet.getInt("CustomerID"),
                        resultSet.getString("CustomerName"),
                        resultSet.getString("Username"),
                        resultSet.getString("Email"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Address"),
                        resultSet.getString("Password"),
                        resultSet.getInt("RoleID"),
                        resultSet.getString("RoleName")
                ));
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to db", e);
        } finally {
            this.close();
        }
        return customers;
    }

    public CustomerBean getCustomer(String username){
        CustomerBean customer = null;
        String sql = "SELECT * FROM customers INNER JOIN users ON customers.Username = users.Username INNER JOIN roles ON users.RoleID = roles.RoleID WHERE customers.Username = ?";
        try{
            this.connect();
            var statement = this.getConnection().prepareStatement(sql);
            statement.setString(1, username);
            var resultSet = statement.executeQuery();
            if(resultSet.next()){
                customer = new CustomerBean(
                        resultSet.getInt("CustomerID"),
                        resultSet.getString("CustomerName"),
                        resultSet.getString("Username"),
                        resultSet.getString("Email"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Address"),
                        resultSet.getString("Password"),
                        resultSet.getInt("RoleID"),
                        resultSet.getString("RoleName")
                );
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to db", e);
        } finally {
            this.close();
        }
        return customer;
    }
}
