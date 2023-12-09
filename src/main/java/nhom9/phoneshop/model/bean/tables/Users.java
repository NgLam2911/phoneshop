package nhom9.phoneshop.model.bean.tables;

public class Users {

    private String Username;
    private String Password;
    private int RoleID;

    public Users(){
    }

    public Users(String Username, String Password, int RoleID){
        this.Username = Username;
        this.Password = Password;
        this.RoleID = RoleID;
    }

    public String getUsername(){
        return Username;
    }

    public String getPassword(){
        return Password;
    }

    public int getRoleID(){
        return RoleID;
    }
}
