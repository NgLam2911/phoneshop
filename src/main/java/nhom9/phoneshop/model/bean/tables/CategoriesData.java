package nhom9.phoneshop.model.bean.tables;

public class CategoriesData {
    private int CategoryID;
    private int ProductID;

    public CategoriesData(){
    }

    public CategoriesData(int CategoryID, int ProductID){
        this.CategoryID = CategoryID;
        this.ProductID = ProductID;
    }

    public int getCategoryID(){
        return CategoryID;
    }

    public int getProductID() {
        return ProductID;
    }
}
