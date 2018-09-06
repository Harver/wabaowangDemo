package dao_admin;

import com.google.gson.Gson;
import model_admin.R;
import model_admin.product_admin;
import util.DBHelp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class product_adminDao {
    static product_admin model = null;

    //取出全部数据
    public List<product_admin> getAll(int page ,int limit) throws SQLException {
        List<product_admin> list = new ArrayList<>();
        int start =(page-1)*limit;
        int end=limit;
        String sql ="select * from product p inner join type t on p.p_t_id=t.t_id order by p.p_id limit "+start+","+end+" ";
        ResultSet rs = new DBHelp().query(sql);
        while (rs.next()){
            //int p_id, BigDecimal p_price, String p_info, String p_photo, int p_state, int p_t_id, String t_nam
            model = new product_admin(
                    rs.getInt("p_id"),
                    rs.getBigDecimal("p_price"),
                    rs.getString("p_info"),
                    rs.getString("p_photo"),
                    rs.getInt("p_state"),
                    rs.getInt("p_t_id"),
                    rs.getString("t_name")
            );
            list.add(model);
        }
        return list;
    }
    //向商品表添加数据
    public boolean admin_product(Object[]in){
        String sql ="insert into product(p_name,p_price,p_info,p_photo,p_state,p_t_id) values('未使用',?,?,?,?,?)";
        int count = new DBHelp().execute(sql,in);
        if(count>0)
            return true;
        return false;
    }

    //向类型表添加数据
    public boolean admin_type_add(Object[]in){
        String sql_query ="select count(t_name) from type where t_name=?";
        ResultSet rs = new DBHelp().query(sql_query,in);
        int counts = 0;
        try {
            if(rs.next()){
                    counts = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(counts==0){
            String sql ="insert into Type(t_name) values(?)";
            int count = new DBHelp().execute(sql,in);
            if (count>0)
                return true;
        }
        return  false;
    }

    //取出类型名称
    public String getTypeName() throws SQLException {
        String sql="select * from type";
        ResultSet rs = new DBHelp().query(sql);
        String html ="[";
        String link="";
        while (rs.next()) {
            html+=link+"{\"t_id\":\""+rs.getInt(1)+"\",\"t_name\":\""+rs.getString(2)+"\"}";
            link=",";
        }
        html +="]";
        return html;
    }
    //修改商品表数据
    public boolean admin_product_update(Object[]in){
        //p_price,p_info,p_photo,p_state,p_t_id
        String sql ="update product set p_price=?,p_info=?,p_photo=?,p_state=?,p_t_id=?  where p_id=? ";
        int count = new DBHelp().execute(sql,in);
        if (count>0)
            return true;
        return false;
    }

    //查看商品表的总条数据
    public int getCount(){
        String sql = "select count(p_id) from product";
        ResultSet rs = new DBHelp().query(sql);
        try {
            if (rs.next()){
                return  rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    //修改商品的状态
    public boolean update_state(int pid,int state){
        String sql ="update product set p_state=? where p_id=?";
        int count = new DBHelp().execute(sql,new Object[]{state,pid});
        if (count>0)
            return true;
        return false;
    }

}
