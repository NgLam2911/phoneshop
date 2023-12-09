package nhom9.phoneshop.model.bean.tables;

public class Customers {
    private int CustomerID;
    private String CustomerName;
    private String Email;
    private String Phone;
    private String Address;
    private String Username;

    public Customers() {
    }

    public Customers(int CustomerID, String CustomerName, String Email, String Phone, String Address, String Username) {
        this.CustomerID = CustomerID;
        this.CustomerName = CustomerName;
        this.Email = Email;
        this.Phone = Phone;
        this.Address = Address;
        this.Username = Username;
    }

    public int getCustomerID() {
        return CustomerID;
    }
    public String getCustomerName() {
        return CustomerName;
    }

    public String getEmail() {
        return Email;
    }

    public String getPhone() {
        return Phone;
    }

    public String getAddress() {
        return Address;
    }

    public String getUsername() {
        return Username;
    }
}
