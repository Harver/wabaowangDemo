package control;

import com.google.gson.Gson;
import dao.ProductDao;
import model.Product;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/product_list","/type_list_id","/search_list"})
public class ProductServlet extends HttpServlet {
    static ProductDao dao = new ProductDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (request.getServletPath()){
            case"/search_list":
                String sql ="select * from product ";
                String value = request.getParameter("value");
                sql +="where p_info like '%"+value+"%' and p_state=1";
                try {
                   List<Product> list= dao.getAll(sql);
                    String result = new Gson().toJson(list);
                    response.getWriter().print(result);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> list = null;
        String sql ="select * from product ";
        switch (request.getServletPath()){
            case "/product_list":
                //默认数据
                try {
                    sql +=" where p_state=1";
                    list= dao.getAll(sql);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case"/type_list_id":
                int li_id = Integer.parseInt(request.getParameter("li_id"));
                sql +="where p_t_id =( select t_id from type where t_id="+li_id+") and p_state=1 ";
                try {
                    list= dao.getAll(sql);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
        String result = new Gson().toJson(list);
        response.getWriter().print(result);
    }
}
