package dao;

import model.Cart_product;
import util.DBHelp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Cart_productDao implements TemplateDao<Cart_product> {
    static  Cart_product model =null;
    @Override
    public List<Cart_product> getAll(String sql) throws SQLException {
        List<Cart_product> list = new ArrayList<>();
        ResultSet rs = new DBHelp().query(sql);
        while (rs.next()){
            //int c_id, int c_num,String state ,BigDecimal p_price, String p_info, String p_photo
            model = new Cart_product(
                    rs.getInt("c_id"),
                    rs.getInt("c_num"),
                    rs.getString("c_state"),
                    rs.getBigDecimal("p_price"),
                    rs.getString("p_info"),
                    rs.getString("p_photo")
            );
            list.add(model);
        }
        return list;
    }

    public boolean delete(String sql,Object[] in){
        int count = new DBHelp().execute(sql,in);
        if(count>0){
            return true;
        }
        return false;
    }
    public boolean update(String sql,Object[] in){
        int count = new DBHelp().execute(sql,in);
        if (count>0)
            return true;

        return false;
    }
    public boolean update(String sql){
        int count = new DBHelp().execute(sql);
        if (count>0)
            return true;

        return false;
    }

    public  String getMoney(String sql){
        ResultSet rs = new DBHelp().query(sql);
        String money ="";
        try {
            if (rs.next()){
                money = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return money;
    }

    @Override
    public Cart_product getInfo(String sql, Object[] in) throws SQLException {
        return null;
    }

    @Override
    public boolean add(Cart_product model) {
        return false;
    }

    public boolean add(String sql,Object[]in){
        int count = new DBHelp().execute(sql,in);
        if(count>0)
            return true;

        return false;
    }

}
