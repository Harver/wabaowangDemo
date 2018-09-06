package dao;

import com.google.gson.Gson;
import model.Order;
import util.DBHelp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao implements TemplateDao<Order> {
    static  Order model =null;
    @Override
    public List<Order> getAll(String sql) throws SQLException {
        List<Order> list = new ArrayList<>();
        ResultSet rs = new DBHelp().query(sql);
        while (rs.next()){
            model = new Order(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getBigDecimal(3),
                    rs.getInt(4),
                    rs.getInt(5)
            );
            list.add(model);
        }
        return list;
    }

    @Override
    public Order getInfo(String sql, Object[] in) throws SQLException {
        return null;
    }

    @Override
    public boolean add(Order model) {
        String sql ="insert into Orders(o_state,o_money,o_ad_id,o_c_id) values(?,?,?,?)";
        int result = new DBHelp().execute(sql,new Object[]{model.getO_state(),model.getO_money(),model.getO_ad_id(),model.getO_c_id()});
        if(result>0)
            return  true;
        return false;
    }

    public  String query(int uid) throws SQLException {
        String sql ="select p.p_photo,p.p_info,c.c_num,o.o_money,ad.ad_address,ad.ad_name from orders o " +
                " inner join cart c on o.o_c_id=c.c_id " +
                " inner join product p on c.c_p_id=p.p_id " +
                " inner join address ad on o.o_ad_id=ad.ad_id " +
                " inner join user u on ad.ad_u_id=u.u_id " +
                " where u.u_id="+uid;

        ResultSet rs = new DBHelp().query(sql);
        String html="[";
        String link="";
        while (rs.next()){
            html+=link+"{\"a\":\""+rs.getString("p_photo")+"\" ,\"b\":\""+rs.getString("p_info")+"\",\"c\":\""+rs.getInt("c_num")
                    +"\",\"d\":\""+rs.getBigDecimal("o_money")+"\",\"e\":\""+rs.getString("ad_address")+"\",\"f\":\""+rs.getString("ad_name")+"\"}";
            link=",";
        }
        html+="]";
        return html;
    }

}
