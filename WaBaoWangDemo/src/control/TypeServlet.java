package control;

import com.google.gson.Gson;
import dao.TypeDao;
import model.Type;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/type_list"})
public class TypeServlet extends HttpServlet {
    static TypeDao dao = new TypeDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Type> list = null;
        String sql ="select * from type";
        try {
            list= dao.getAll(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String result = new Gson().toJson(list);
        response.getWriter().print(result);
    }
}
