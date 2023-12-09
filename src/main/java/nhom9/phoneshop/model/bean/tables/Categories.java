package nhom9.phoneshop.model.bean.tables;

public class Categories {

    private int CategoryID;
    private int CategoryName;

    public Categories(){
    }

    public Categories(int CategoryID, int CategoryName){
        this.CategoryID = CategoryID;
        this.CategoryName = CategoryName;
    }

    public int getCategoryID(){
        return CategoryID;
    }

    public int getCategoryName(){
        return CategoryName;
    }
}
