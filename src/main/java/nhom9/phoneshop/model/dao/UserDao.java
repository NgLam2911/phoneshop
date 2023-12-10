package nhom9.phoneshop.model.dao;

public class UserDao extends BaseDao{

    public boolean login(String username, String password) {
        boolean result = false;
        String sql = "SELECT * FROM users WHERE Username = ? AND Password = ?";
        try {
            this.connect();
            var statement = this.getConnection().prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
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

    public void register(String customerName, String username, String email, String phoneNumber, String password, String address, int roleID) {
        String sql1 = "INSERT INTO users (Username, Password, RoleID) VALUES (?, ?, ?)";
        String sql2 = "INSERT INTO customers (CustomerName, Username, Email, Phone, Address) VALUES (?, ?, ?, ?, ?)";
        try {
            this.connect();
            var statement1 = this.getConnection().prepareStatement(sql1);
            statement1.setString(1, username);
            statement1.setString(2, password);
            statement1.setInt(3, roleID);
            statement1.executeUpdate();
            var statement2 = this.getConnection().prepareStatement(sql2);
            statement2.setString(1, customerName);
            statement2.setString(2, username);
            statement2.setString(3, email);
            statement2.setString(4, phoneNumber);
            statement2.setString(5, address);
            statement2.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to db", e);
        } finally {
            this.close();
        }
    }
}
