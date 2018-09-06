package control;

import com.google.gson.Gson;
import dao.AddressDao;
import model.Address;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns ={"/address_list","/address_lala"})
public class AddressServlet extends HttpServlet {
    static AddressDao dao = new AddressDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (request.getServletPath()){
            case "/address_list":
                int u_id = Integer.parseInt(request.getParameter("u_id"));
                String sql = "select * from address where ad_u_id="+u_id+"";
                List<Address> list=null;
                try {
                    list = dao.getAll(sql);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                response.getWriter().print(new Gson().toJson(list));
                break;
        }
    }
}
