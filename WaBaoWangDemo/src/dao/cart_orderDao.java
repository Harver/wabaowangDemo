package dao;

import model.cart_order;
import util.DBHelp;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class cart_orderDao implements TemplateDao<cart_order> {
    static cart_order model =null;
    @Override
    public List<cart_order> getAll(String sql) throws SQLException {
        List<cart_order> list = new ArrayList<>();
        ResultSet rs = new DBHelp().query(sql);
        while (rs.next()){
            //int c_id, String p_photo, String p_info, BigDecimal p_price, int c_num, BigDecimal c_sum
            model = new cart_order(
                    rs.getInt("c_id"),
                    rs.getString("p_photo"),
                    rs.getString("p_info"),
                    rs.getBigDecimal("p_price"),
                    rs.getInt("c_num"),
                    rs.getBigDecimal("c_sum")
            );
            list.add(model);
        }
       return list;
    }

    @Override
    public cart_order getInfo(String sql, Object[] in) throws SQLException {
        return null;
    }

    @Override
    public boolean add(cart_order model) {
        return false;
    }

    public BigDecimal getsumMoney(String cid){
        String sql="select sum(c_sum) from cart c inner join product p on c.c_p_id=p.p_id " +
                " where c.c_id in("+cid+") and c.c_state=0";
        ResultSet rs = new DBHelp().query(sql);
        try {
            if (rs.next())
            return  rs.getBigDecimal(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new BigDecimal(0);
    }
}
