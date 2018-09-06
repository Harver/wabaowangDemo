package dao;

import java.sql.SQLException;
import java.util.List;

public interface TemplateDao<T> {
    //取到所有数据
    List<T> getAll(String sql) throws SQLException;
    //取到对象数据
     T getInfo(String sql,Object[]in)throws SQLException;
    //添加对象
    boolean add(T model);
}
