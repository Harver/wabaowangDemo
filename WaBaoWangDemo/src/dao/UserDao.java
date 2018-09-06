package dao;

import model.User;
import util.DBHelp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements TemplateDao<User> {
    static  User model =null;
    @Override
    public List<User> getAll(String sql) throws SQLException {
        List<User> list = new ArrayList<>();
        ResultSet rs = new DBHelp().query(sql);
        while (rs.next()) {
            //int u_id, String u_name, String u_pwd, String u_tel, String u_email
            model = new User(
                 rs.getInt(1),
                 rs.getString(2),
                 rs.getString(3),
                 rs.getString(4),
                 rs.getString(5)
            );
            list.add(model);
        }
        return list;
    }

    @Override
    public User getInfo(String sql,Object[]in) throws SQLException {
        ResultSet rs =  new DBHelp().query(sql,in);
        while (rs.next()) {
            //int u_id, String u_name, String u_pwd, String u_tel, String u_email
            model = new User(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)
            );
            return model;
        }
        return null;
    }

    @Override
    public boolean add(User model) {
        String sql ="insert into User(u_name,u_pwd,u_tel,u_email) values(?,?,?,?) ";
        int result = new DBHelp().execute(sql,new Object[]{model.getU_name(),model.getU_pwd(),model.getU_tel(),model.getU_email()});
        if(result>0){
            return true;
        }
        return false;
    }
}
