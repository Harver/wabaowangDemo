package control;

import dao.UserDao;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/login","/reg","/sel_user"})
public class UserServlet extends HttpServlet {
    static UserDao dao = new UserDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user=null;
        switch (request.getServletPath()){
            case "/login":
                String account = request.getParameter("account");
                String pwd = request.getParameter("password");

                try {
                    String sql ="select * from User where u_name =? and u_pwd=? or u_tel=? and u_pwd=? or u_email=?  and u_pwd=? ";
                    user= dao.getInfo(sql,new Object[]{account,pwd,account,pwd,account,pwd});
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if(user!=null){
                    request.getSession().setAttribute("user",user);
                    response.getWriter().print("{\"msg\":\"登陆成功\"}");
                }else{
                    response.getWriter().print("{\"msg\":\"登陆失败\"}");
                }
            break;
            case "/reg":
                String u_name = request.getParameter("account");
                String u_pwd = request.getParameter("password");
                String u_tel = request.getParameter("tel");
                String u_email = request.getParameter("email");
                boolean flag=dao.add(new User(u_name,u_pwd,u_tel,u_email));
                if(flag){
                    String sql ="select * from User where u_name=? and u_pwd=?";
                    try {
                        user =  dao.getInfo(sql,new Object[]{u_name,u_pwd});
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    request.getSession().setAttribute("user",user);
                    response.getWriter().print("{\"msg\":\"注册成功\"}");
                }else{
                    response.getWriter().print("{\"msg\":\"注册失败\"}");
                }
            break;
            case "/sel_user":
                  user= (User) request.getSession().getAttribute("user");
                 if(user!=null){
                     response.getWriter().print("{\"name\":\""+user.getU_name()+"\",\"u_id\":\""+user.getU_id()+"\"}");
                 }else{
                     response.getWriter().print("{\"msg\":\"无\",}");
                 }
                break;
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
