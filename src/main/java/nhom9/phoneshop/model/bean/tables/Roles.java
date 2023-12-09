package nhom9.phoneshop.model.bean.tables;

public class Roles {

    private int RoleID;
    private String RoleName;

    public Roles(){
    }

    public Roles(int RoleID, String RoleName){
        this.RoleID = RoleID;
        this.RoleName = RoleName;
    }

    public int getRoleID(){
        return RoleID;
    }

    public String getRoleName(){
        return RoleName;
    }
}
