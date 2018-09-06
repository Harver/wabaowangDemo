package dao;

import model.Address;
import util.DBHelp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressDao implements TemplateDao<Address> {
    static  Address model = null;
    @Override
    public List<Address> getAll(String sql) throws SQLException {
        List<Address> list = new ArrayList<>();
        ResultSet rs = new DBHelp().query(sql);
        while (rs.next()){
            //int ad_id, String ad_name, String ad_address, String ad_tel, int ad_u_id
            model = new Address(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5)
            );
            list.add(model);
        }
        return list;
    }

    @Override
    public Address getInfo(String sql, Object[] in) throws SQLException {
        return null;
    }

    @Override
    public boolean add(Address model) {
        String sql ="insert into Address(ad_name,ad_address,ad_tel,ad_u_id) values(?,?,?,?)";
        int result = new DBHelp().execute(sql,new Object[]{model.getAd_name(),model.getAd_address(),model.getAd_tel(),model.getAd_u_id()});
        if(result>0)
            return true;
        return false;
    }
}
