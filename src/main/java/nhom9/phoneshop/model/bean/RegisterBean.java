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
}
