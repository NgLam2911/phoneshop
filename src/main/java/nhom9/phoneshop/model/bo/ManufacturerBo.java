package nhom9.phoneshop.model.bo;

import java.util.ArrayList;

import nhom9.phoneshop.model.bean.tables.Manufacturers;
import nhom9.phoneshop.model.dao.ManufacturerDao;

public class ManufacturerBo {
    public ArrayList<Manufacturers> getAllManufacturers() {
        return (new ManufacturerDao()).getAllManufacturers();
    }
}
