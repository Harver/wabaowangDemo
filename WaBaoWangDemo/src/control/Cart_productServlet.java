package control;

import com.google.gson.Gson;
import dao.Cart_productDao;
import model.Cart_product;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/cart_product_list","/cart_product_del","/cart_product_update","/cart_product_money","/cart_product_total"})
public class Cart_productServlet extends HttpServlet {
     static Cart_productDao dao= new Cart_productDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean flag=false;
        switch (request.getServletPath()){
            case"/cart_product_del":
                int c_id = Integer.parseInt(request.getParameter("c_id"));
                String sql ="delete from cart where c_id=?";
                 flag = dao.delete(sql,new Object[]{c_id}) ;
                if(flag){
                    response.getWriter().print("{\"msg\":\"删除成功!!!\"}");
                }else{
                    response.getWriter().print("{\"msg\":\"删除失败!!!\"}");
                }
                break;
            case "/cart_product_update":
                int update_c_id= Integer.parseInt(request.getParameter("c_id"));
                int update_c_u_id = Integer.parseInt(request.getParameter("c_u_id"));
                int update_num = Integer.parseInt(request.getParameter("c_num"));
                BigDecimal update_money = new BigDecimal(request.getParameter("c_money"));
                String sql_update ="update Cart set c_num=?,c_sum=? where c_id=? and c_u_id=?";
                flag = dao.update(sql_update,new Object[]{update_num,update_money,update_c_id,update_c_u_id});
                if(flag){
                    response.getWriter().print("{\"msg\":\"数量修改成功!!!\"}");
                }else {
                    response.getWriter().print("{\"msg\":\"数量修改失败哦，少侠!!!\"}");
                }
                break;
            case "/cart_product_money":
                String c_ids = request.getParameter("c_ids");
                String sql_money ="select sum(c_sum) from cart where c_id in("+c_ids+")";

                String money = dao.getMoney(sql_money);
                if(money!=null){
                    response.getWriter().print("{\"msg\":\""+money+"\"}");
                }else{
                    response.getWriter().print("{\"msg\":\""+money+"\"}");
                }

                break;
            case"/cart_product_total":
                String [] arr_cid = request.getParameterValues("arr_c_id");
                if(arr_cid.length!=0){
                    //将arr_cid 存在再sessionzhong
                    request.getSession().setAttribute("arr_cid",arr_cid[0]);
                    response.getWriter().print("{\"msg\":\"1\"}");
                }else{
                    response.getWriter().print("{\"msg\":\"0\"}");
                }


                //int total_c_u_id = Integer.parseInt(request.getParameter("c_u_id"));
                //BigDecimal sum_money = new BigDecimal(request.getParameter("c_sum"));
                //先修改购物车 这条订单的状态
//                arr_cid=arr_cid[0].split(",");
//                for (int i =0;i<arr_cid.length;i++) {
//                    String total_sql = "update cart set c_state='已购' where c_id=?";
//                    flag=dao.update(total_sql,new Object[]{Integer.parseInt(arr_cid[i])});
//                    if(!flag){
//                        continue;
//                    }
//                }
//                if (flag){
//                    response.getWriter().print("{\"msg\":\"提交成功,♪(^∇^*)\"}");
//                }else {
//                    response.getWriter().print("{\"msg\":\"提交失败\"}");
//                }
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (request.getServletPath()){
            case "/cart_product_list":
                //用户的id
                int c_u_id = Integer.parseInt(request.getParameter("u_id"));
                //验证session保存用户id是否一致
                User info = (User) request.getSession().getAttribute("user");
                if(info.getU_id()==c_u_id){
                    try {
                        String  sql ="select * from cart c" +
                                " inner join product p on c.c_p_id=p.p_id " +
                                " where c.c_u_id ="+c_u_id;
                        List<Cart_product> list = dao.getAll(sql);
                        if (list!=null){
                            String s = new Gson().toJson(list);
                            response.getWriter().print(s);
                        }else{
                            response.getWriter().print("");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }else{
                    response.getWriter().print("{\"msg\":\"请先登陆.....\"}");
                }

                break;
        }
    }
}
