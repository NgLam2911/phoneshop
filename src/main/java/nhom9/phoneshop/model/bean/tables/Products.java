package nhom9.phoneshop.model.bean.tables;

public class Products {

    private int ProductID;
    private String ProductName;
    private double Price;
    private int ManufacturerID;
    private String CPU;
    private String RAM;
    private String DisplaySize;
    private int DisplayWidth;
    private int DisplayHeight;
    private String OS;
    private String Battery;
    private double Capacity;

    private String Image;

    public Products() {
    }

    public Products(int ProductID, String ProductName, double Price, int ManufacturerID, String CPU, String RAM, String DisplaySize, int DisplayWidth, int DisplayHeight, String OS, String Battery, double Capacity, String Image) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.Price = Price;
        this.ManufacturerID = ManufacturerID;
        this.CPU = CPU;
        this.RAM = RAM;
        this.DisplaySize = DisplaySize;
        this.DisplayWidth = DisplayWidth;
        this.DisplayHeight = DisplayHeight;
        this.OS = OS;
        this.Battery = Battery;
        this.Capacity = Capacity;
        this.Image = Image;
    }

    public int getProductID() {
        return ProductID;
    }

    public String getProductName() {
        return ProductName;
    }

    public double getPrice() {
        return Price;
    }

    public int getManufacturerID() {
        return ManufacturerID;
    }

    public String getCPU() {
        return CPU;
    }

    public String getRAM() {
        return RAM;
    }

    public String getDisplaySize() {
        return DisplaySize;
    }

    public int getDisplayWidth() {
        return DisplayWidth;
    }

    public int getDisplayHeight() {
        return DisplayHeight;
    }

    public String getOS() {
        return OS;
    }

    public String getBattery() {
        return Battery;
    }

    public double getCapacity() {
        return Capacity;
    }

    public String getImage(){
        return this.Image;
    }
}
