package nhom9.phoneshop.model.bean.tables;

public class Manufacturers {

    private int ManufacturerID;
    private String ManufacturerName;

    public Manufacturers() {
    }

    public Manufacturers(int ManufacturerID, String ManufacturerName) {
        this.ManufacturerID = ManufacturerID;
        this.ManufacturerName = ManufacturerName;
    }

    public int getManufacturerID() {
        return ManufacturerID;
    }

    public String getManufacturerName() {
        return ManufacturerName;
    }
}
