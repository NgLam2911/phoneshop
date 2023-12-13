package nhom9.phoneshop.model.bean;

public class UserBean {

    private String Username;
    private String Password;
    private int RoleID;
    private String RoleName;

    public UserBean() {
    }

    public UserBean(String Username, String Password, int RoleID, String RoleName) {
        this.Username = Username;
        this.Password = Password;
        this.RoleID = RoleID;
        this.RoleName = RoleName;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public int getRoleID() {
        return RoleID;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setRoleID(int RoleID) {
        this.RoleID = RoleID;
    }

    public void setRoleName(String RoleName) {
        this.RoleName = RoleName;
    }
}
