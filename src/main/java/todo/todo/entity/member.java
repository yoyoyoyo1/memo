package todo.todo.entity;

public class member {
    Integer id;
    String name;
    String password;
    String mail;
    Boolean activate;
    member(){}
    member(Integer id,String name,String password,String mail,boolean activate){
        this.password = password;
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.activate = activate;
    }

    public Boolean getActivate() {
        return activate;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getMail() {
        return mail;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActivate(Boolean activate) {
        this.activate = activate;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
