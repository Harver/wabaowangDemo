package model;

public class User {
//    u_id int primary key auto_increment COMMENT '用户编号',
//    u_name varchar(20) NOT NULL COMMENT '用户账号',
//    u_pwd VARCHAR(20) not null COMMENT '用户密码',
//    u_tel VARCHAR(11) not null COMMENT '用户电话',
//    u_email VARCHAR(20) not null COMMENT '用户邮箱'

    private  int u_id;
    private  String u_name;
    private String u_pwd;
    private String u_tel;
    private String u_email;

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_pwd() {
        return u_pwd;
    }

    public void setU_pwd(String u_pwd) {
        this.u_pwd = u_pwd;
    }

    public String getU_tel() {
        return u_tel;
    }

    public void setU_tel(String u_tel) {
        this.u_tel = u_tel;
    }

    public String getU_email() {
        return u_email;
    }

    public void setU_email(String u_email) {
        this.u_email = u_email;
    }

    public User(int u_id, String u_name, String u_pwd, String u_tel, String u_email) {
        this.u_id = u_id;
        this.u_name = u_name;
        this.u_pwd = u_pwd;
        this.u_tel = u_tel;
        this.u_email = u_email;
    }
    public User(String u_name, String u_pwd, String u_tel, String u_email) {
        this.u_name = u_name;
        this.u_pwd = u_pwd;
        this.u_tel = u_tel;
        this.u_email = u_email;
    }
    public User() { }



}
