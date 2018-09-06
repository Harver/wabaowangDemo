package control;

import com.google.gson.Gson;
import dao.AddressDao;
import dao.CartDao;
import dao.OrderDao;
import dao.cart_orderDao;
import model.Address;
import model.Order;
import model.User;
import model.cart_order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns ={"/take_info","/take_list","/task_sumMoney","/order_commit","/new_address","/data_record_list"})
public class OrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean flag = false;
        switch (request.getServletPath()){
            case "/order_commit":
                //取出购物车编号
                String c_id = (String) request.getSession().getAttribute("arr_cid");
                int o_ad_id = Integer.parseInt(request.getParameter("ad_id"));
                String money = request.getParameter("o_money");
                //修改购物车状态，同时添加到订单表中
                String update_cart_sql ="update cart set c_state=1 where c_id in("+c_id+")";
                flag =new CartDao().update(update_cart_sql);
                if(flag){
                    String [] c_ids = c_id.split(",");
                    for (int i = 0; i <c_ids.length ; i++) {
                        flag = new  OrderDao().add(new Order(1,new BigDecimal(money),o_ad_id,Integer.parseInt(c_ids[i])));
                        if(!flag){
                            continue;
                        }
                    }
                    if (flag){
                        response.getWriter().print("{\"msg\":\"提交成功,♪(^∇^*)\"}");
                    }else{
                        response.getWriter().print("{\"msg\":\"提交失败哦，少侠\"}");
                    }
                }else{
                    response.getWriter().print("{\"msg\":\"出现异常，少侠\"}");
                }
                break;
            case "/new_address":
                String user = request.getParameter("user");
                String tell = request.getParameter("tell");
                String address = request.getParameter("address");
                User u_id = (User)request.getSession().getAttribute("user");
                if(u_id!=null){
                    flag = new AddressDao().add(new Address(user,address,tell,u_id.getU_id()));
                    if(flag){
                        response.getWriter().print("{\"msg\":\"添加成功,♪(^∇^*)\"}");
                    }else{
                        response.getWriter().print("{\"msg\":\"添加失败...\"}");
                    }
                }else{
                    response.getWriter().print("{\"msg\":\"添加异常\"}");
                }
                    break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (request.getServletPath()){
            case "/take_info":
                User user= (User) request.getSession().getAttribute("user");
                response.getWriter().print("{\"uid\":\""+user.getU_id()+"\",\"uname\":\""+user.getU_name()+"\"}");
                break;
            case "/take_list":
                String arr_cid = (String) request.getSession().getAttribute("arr_cid");
                String sql="select * from cart c inner join product p on c.c_p_id=p.p_id " +
                        " where c.c_id in("+arr_cid+") and c.c_state=0";
                try {
                    List<cart_order> cart_order_list = new cart_orderDao().getAll(sql);
                    response.getWriter().print(new Gson().toJson(cart_order_list));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "/task_sumMoney":
                String sum_money = (String) request.getSession().getAttribute("arr_cid");
                BigDecimal sum = new cart_orderDao().getsumMoney(sum_money);
                if(sum!=null){
                    response.getWriter().print("{\"money\":\""+sum+"\"}");
                }else{
                    response.getWriter().print("{\"money\":\"0.00\"}");
                }

                break;
            case"/data_record_list":
                User uid = (User) request.getSession().getAttribute("user");
                if(uid!=null){
                    try {
                        String result = new OrderDao().query(uid.getU_id());
                        response.getWriter().print(result);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }else{
                    response.getWriter().print("{\"msg\":\"信息获取失败\"}");
                }
                break;
        }
    }
}
