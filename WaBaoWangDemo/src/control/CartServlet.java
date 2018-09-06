package control;

import dao.CartDao;
import model.Cart;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/cart_list","/add_cart","/cart_count"})
public class CartServlet extends HttpServlet {
    static CartDao dao = new CartDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart info =null;
        switch (request.getServletPath()){
            case "/add_cart":
                int u_id = Integer.parseInt(request.getParameter("u_id"));
                int p_id = Integer.parseInt(request.getParameter("p_id"));
                String money = request.getParameter("c_money");
                //select * from Cart
                //先判断此用户有没有购物车订单
                String sql ="select * from Cart where c_u_id=? and c_p_id=? ";
                boolean flag = dao.IsUser(sql,new Object[]{u_id,p_id});
                //当购物车编号不存在时
                if(!flag){
                   boolean add=dao.add(new Cart("0",1,new BigDecimal(money),u_id,p_id));
                   if(add){
                       response.getWriter().print("{\"msg\":\"加入购物车成功..\"}");
                   }else
                        response.getWriter().print("{\"msg\":\"加入购物车失败..\"}");
                }else{
                    //当购物车编号存在时
                    //String update_sql ="select * from Cart where c_u_id=? and c_p_id=? ";
                    try {
                        info = dao.getInfo(sql,new Object[]{u_id,p_id});
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if(info!=null){
                        //数量
                        int count = info.getC_num()+1;
                        //金额
                        BigDecimal sum_moeny = info.getC_sum();
                        //执行修改
                        String up_add = "update Cart set c_num=?,c_sum=? where c_u_id=? and c_p_id=? and c_state=0";
                        boolean update = dao.update(up_add,new Object[]{count,sum_moeny,u_id,p_id});
                        if(update){
                            response.getWriter().print("{\"msg\":\"加入购物车成功..\"}");
                        }else {
                            //新增购物车编号,状态为0
                            boolean add=dao.add(new Cart("0",1,new BigDecimal(money),u_id,p_id));
                            if(add){
                                response.getWriter().print("{\"msg\":\"加入购物车成功..\"}");
                            }else{
                                response.getWriter().print("{\"msg\":\"加入购物车失败..\"}");
                            }
                        }
                    }
                }
                break;
            case"/cart_count":
                //用户id
                int c_u_id = Integer.parseInt(request.getParameter("c_u_id"));
                //取出保存在session里的用户信息
                User user = (User) request.getSession().getAttribute("user");
                //验证页面的用户id和session里的id
                if(user.getU_id()==c_u_id){
                    int count = dao.cart_count(c_u_id);
                    response.getWriter().print("{\"count\":"+count+"}");
                }else{
                    response.getWriter().print("{\"count\":"+ 0.0 +"}");
                }

                break;
        }
    }
}
