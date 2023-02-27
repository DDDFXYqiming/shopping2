package entity;

public class user {
    private int id;//用户id
    private String name;//用户名
    private String password;//用户密码
    private String email;
    public int status;

    public user(){}

    public user(String name,String password,String email,int status){
        this.name = name;
        this.password = password;
        this.email = email;
        this.status = status;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
