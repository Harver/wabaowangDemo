package dao;

import model.Type;
import util.DBHelp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TypeDao implements TemplateDao<Type> {
    static Type model = null;
    @Override
    public List<Type> getAll(String sql) throws SQLException {
        List<Type> list = new ArrayList<>();
        ResultSet rs = new DBHelp().query(sql);
        while (rs.next()) {
            model = new Type(
                    rs.getInt(1),
                    rs.getString(2)
            );
            list.add(model);
        }
        return list;
    }

    @Override
    public Type getInfo(String sql, Object[] in) throws SQLException {
        return null;
    }

    @Override
    public boolean add(Type model) {
        return false;
    }
}
