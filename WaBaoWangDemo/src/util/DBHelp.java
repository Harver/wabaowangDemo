package util;

import java.math.BigDecimal;
import java.sql.*;

public class DBHelp {

    //mysql 驱动
    private static  String driver;
    //连接数据库
    private static String url;
    //登陆账号
    private static String uid;
    //登陆密码
    private static  String pwd;
    //连接对象
    static Connection conn = null;
    //命令对象
    static Statement stmt = null;
    static PreparedStatement pstmt=null;
    static {
        driver = "com.mysql.jdbc.Driver";
        //?characterEncoding=UTF-8
        url = "jdbc:mysql://localhost:3306/shop?characterEncoding=UTF-8";
        uid = "oukele";
        pwd = "oukele";
    }

    //打开数据库
    public static  void open(){
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,uid,pwd);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    //结果集返回数据
    public static ResultSet query(String sql){
        open();
        try {
            stmt = conn.createStatement();
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //查询 参数化
    public static ResultSet query(String sql,Object[]in){
        open();
        try {
            pstmt = conn.prepareStatement(sql);
            for(int i=0;i<in.length;i++)
                pstmt.setObject(i+1,in[i]);
            return pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //数据增删改
    public static int execute(String sql,Object[]in){
        open();
        try {
            pstmt = conn.prepareStatement(sql);
            for (int i=0;i<in.length;i++)
                pstmt.setObject(i+1,in[i]);
            return  pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    //
    public static int execute(String sql){
        open();
        try {
            stmt = conn.createStatement();
            return stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}

class Test{
    public static void main(String[] args) throws SQLException {

    }
}
