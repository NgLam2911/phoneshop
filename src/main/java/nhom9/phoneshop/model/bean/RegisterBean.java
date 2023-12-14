package nhom9.phoneshop.model.bean;

public class RegisterBean {

    private String customerName;
    private String username;
    private String email;
    private String phoneNumber;
    private String password;

    public RegisterBean() {
    }

    public RegisterBean(String customerName, String username, String email, String phoneNumber, String password) {
        this.customerName = customerName;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
