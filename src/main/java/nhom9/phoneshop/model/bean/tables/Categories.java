package nhom9.phoneshop.model.bean.tables;

public class Categories {

    private int CategoryID;
    private String CategoryName;

    public Categories(){
    }

    public Categories(int CategoryID, String CategoryName){
        this.CategoryID = CategoryID;
        this.CategoryName = CategoryName;
    }

    public int getCategoryID(){
        return CategoryID;
    }

    public String getCategoryName(){
        return CategoryName;
    }
}
