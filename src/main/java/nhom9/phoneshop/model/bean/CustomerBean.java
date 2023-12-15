package nhom9.phoneshop.model.bean;

public class CustomerBean {

    private int CustomerID;
    private String CustomerName;
    private String Username;
    private String Email;
    private String Phone;
    private String Address;
    private String Password;
    private int RoleID;
    private String RoleName;

    public CustomerBean() {
    }

    public CustomerBean(int CustomerID, String CustomerName, String Username, String Email, String Phone, String Address, String Password, int RoleID, String RoleName) {
        this.CustomerID = CustomerID;
        this.CustomerName = CustomerName;
        this.Username = Username;
        this.Email = Email;
        this.Phone = Phone;
        this.Address = Address;
        this.Password = Password;
        this.RoleID = RoleID;
        this.RoleName = RoleName;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public String getUsername() {
        return Username;
    }

    public String getEmail() {
        return Email;
    }

    public String getPhoneNumber() {
        return Phone;
    }

    public String getAddress() {
        return Address;
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

    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.Phone = PhoneNumber;
    }

    public void setAddress(String Address) {
        this.Address = Address;
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
