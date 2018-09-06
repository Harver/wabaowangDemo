package dao;

import model.Product;
import util.DBHelp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements TemplateDao<Product> {
    static Product model = null;
    @Override
    public List<Product> getAll(String sql) throws SQLException {
        List<Product> list = new ArrayList<>();
        ResultSet rs = new DBHelp().query(sql);
        while (rs.next()) {
           // int p_id, String p_name, BigDecimal p_price, String p_info, String p_photo, String p_state
            model = new Product(
                   rs.getInt(1),
                   rs.getString(2),
                   rs.getBigDecimal(3),
                   rs.getString(4),
                   rs.getString(5),
                   rs.getString(6)
            );
            list.add(model);
        }
        return list;
    }

    @Override
    public Product getInfo(String sql, Object[] in) throws SQLException {
        return null;
    }

    @Override
    public boolean add(Product model) {
        return false;
    }

}
