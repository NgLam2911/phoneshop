package nhom9.phoneshop.model.dao;

import nhom9.phoneshop.model.bean.tables.Roles;

import java.util.ArrayList;

public class RoleDao extends BaseDao{

    public int getRoleID(String roleName){
        int roleID = -1;
        String sql = "SELECT RoleID FROM roles WHERE RoleName = ?";
        try {
            this.connect();
            var statement = this.getConnection().prepareStatement(sql);
            statement.setString(1, roleName);
            var resultSet = statement.executeQuery();
            if (resultSet.next()){
                roleID = resultSet.getInt("RoleID");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to db", e);
        } finally {
            this.close();
        }
        return roleID;
    }

    public String getRoleName(int roleID){
        String roleName = "";
        String sql = "SELECT RoleName FROM roles WHERE RoleID = ?";
        try {
            this.connect();
            var statement = this.getConnection().prepareStatement(sql);
            statement.setInt(1, roleID);
            var resultSet = statement.executeQuery();
            if (resultSet.next()){
                roleName = resultSet.getString("RoleName");
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to db", e);
        } finally {
            this.close();
        }
        return roleName;
    }

    public ArrayList<Roles> getAllRoles() {
        String sql = "SELECT * FROM roles";
        try {
            this.connect();
            var statement = this.getConnection().prepareStatement(sql);
            var resultSet = statement.executeQuery();
            ArrayList<Roles> roles = new ArrayList<>();
            while (resultSet.next()){
                int roleID = resultSet.getInt("RoleID");
                String roleName = resultSet.getString("RoleName");
                roles.add(new Roles(roleID, roleName));
            }
            return roles;
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to db", e);
        } finally {
            this.close();
        }
    }

    public void addRole(String roleName){
        String sql = "INSERT INTO roles (RoleName) VALUES (?)";
        try{
            this.connect();
            var statement = this.getConnection().prepareStatement(sql);
            statement.setString(1, roleName);
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to db", e);
        } finally {
            this.close();
        }
    }

    public void updateRole(int roleID, String roleName){
        String sql = "UPDATE roles SET RoleName = ? WHERE RoleID = ?";
        try{
            this.connect();
            var statement = this.getConnection().prepareStatement(sql);
            statement.setString(1, roleName);
            statement.setInt(2, roleID);
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to db", e);
        } finally {
            this.close();
        }
    }

    public void deleteRole(int roleID){
        String sql = "DELETE FROM roles WHERE RoleID = ?";
        try{
            this.connect();
            var statement = this.getConnection().prepareStatement(sql);
            statement.setInt(1, roleID);
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to db", e);
        } finally {
            this.close();
        }
    }
}
