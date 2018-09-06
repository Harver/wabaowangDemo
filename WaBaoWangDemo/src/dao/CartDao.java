package dao;

import model.Cart;
import util.DBHelp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDao implements TemplateDao<Cart> {
    static Cart model = null;
    @Override
    public List<Cart> getAll(String sql) throws SQLException {
        List<Cart> list = new ArrayList<>();
        ResultSet rs = new DBHelp().query(sql);
        while (rs.next()) {
            //int c_id, String c_state, int c_num, BigDecimal c_sum, int c_u_id, int c_p_id
            model = new Cart(
                rs.getInt(1),
                rs.getString(2),
                rs.getInt(3),
                rs.getBigDecimal(4),
                rs.getInt(5),
                rs.getInt(6)
            );
            list.add(model);
        }
        return list;
    }


    @Override
    public Cart getInfo(String sql, Object[] in) throws SQLException {
        ResultSet rs = new DBHelp().query(sql,in);
        while (rs.next()) {
            //int c_id, String c_state, int c_num, BigDecimal c_sum, int c_u_id, int c_p_id
            model = new Cart(
              rs.getInt(1),
              rs.getString(2),
              rs.getInt(3),
              rs.getBigDecimal(4),
              rs.getInt(5),
              rs.getInt(6)
            );
        }
        return model;
    }

    public boolean IsUser(String sql,Object[]in){
        ResultSet rs = new DBHelp().query(sql,in);
        try {
            if(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int cart_count(int c_u_id){
        String sql ="select sum(c_num) s from Cart where c_u_id=? and c_state=? ";
        ResultSet rs = new DBHelp().query(sql,new Object[]{c_u_id,"0"});
        try {
            if(rs.next()){
                System.out.println(rs.getInt(1));
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean add(Cart model) {
        String sql ="insert into Cart(c_state,c_num,c_sum,c_u_id,c_p_id) values(?,?,?,?,?)";
        int result = new DBHelp().execute(sql,new Object[]{model.getC_state(),model.getC_num(),model.getC_sum(),model.getC_u_id(),model.getC_p_id()});
        if(result>0){
            return true;
        }
        return false;
    }

    public boolean update(String sql,Object[]in){
        int result = new DBHelp().execute(sql,in);
        if(result>0){
            return true;
        }
        return false;
    }
    public boolean update(String sql){
        int result = new DBHelp().execute(sql);
        if(result>0){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int s = new CartDao().cart_count(15);
        System.out.println(s);
    }
}
