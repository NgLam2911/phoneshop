package nhom9.phoneshop.model.bean;

public class ProductBean {

    private int ProductID;
    private String ProductName;
    private double Price;
    private int ManufacturerID;

    private String ManufacturerName;
    private String CPU;
    private String RAM;
    private String DisplaySize;
    private int DisplayWidth;
    private int DisplayHeight;
    private String OS;
    private String Battery;
    private double Capacity;
    private String Image;

    private int Quantity;

    private String Color;

    public ProductBean() {
    }

    public ProductBean(int ProductID, String ProductName, double Price, int ManufacturerID, String ManufacturerName, String CPU, String RAM, String DisplaySize, int DisplayWidth, int DisplayHeight, String OS, String Battery, double Capacity, String Image, int Quantity, String Color) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.Price = Price;
        this.ManufacturerID = ManufacturerID;
        this.ManufacturerName = ManufacturerName;
        this.CPU = CPU;
        this.RAM = RAM;
        this.DisplaySize = DisplaySize;
        this.DisplayWidth = DisplayWidth;
        this.DisplayHeight = DisplayHeight;
        this.OS = OS;
        this.Battery = Battery;
        this.Capacity = Capacity;
        this.Image = Image;
        this.Quantity = Quantity;
        this.Color = Color;
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

    public String getManufacturerName() {
        return ManufacturerName;
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

    public String getImage() {
        return Image;
    }

    public int getQuantity(){
        return this.Quantity;
    }

    public String getColor(){
        return this.Color;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public void setManufacturerID(int ManufacturerID) {
        this.ManufacturerID = ManufacturerID;
    }

    public void setManufacturerName(String ManufacturerName) {
        this.ManufacturerName = ManufacturerName;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public void setRAM(String RAM) {
        this.RAM = RAM;
    }

    public void setDisplaySize(String DisplaySize) {
        this.DisplaySize = DisplaySize;
    }

    public void setDisplayWidth(int DisplayWidth) {
        this.DisplayWidth = DisplayWidth;
    }

    public void setDisplayHeight(int DisplayHeight) {
        this.DisplayHeight = DisplayHeight;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }

    public void setBattery(String Battery) {
        this.Battery = Battery;
    }

    public void setCapacity(double Capacity) {
        this.Capacity = Capacity;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public void setQuantity(int Quantity){
        this.Quantity = Quantity;
    }

    public void setColor(String Color){
        this.Color = Color;
    }
}
