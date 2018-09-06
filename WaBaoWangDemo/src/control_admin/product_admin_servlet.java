package control_admin;

import com.google.gson.Gson;
import dao_admin.product_adminDao;
import model_admin.R;
import model_admin.product_admin;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;


@WebServlet(urlPatterns = {"/p_admin_list","/p_type_list","/p_admin_add","/p_admin_img","/p_admin_type_name","/p_admin_update","/p_admin_state"})
public class product_admin_servlet extends HttpServlet {
    String imgName="";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       boolean flag =false;
        switch (request.getServletPath()){
            case "/p_admin_add":
                String p_info =request.getParameter("p_info");
                int t_id=Integer.parseInt(request.getParameter("t_id"));
                BigDecimal p_price = new BigDecimal(request.getParameter("p_price"));
                int p_state = Integer.parseInt(request.getParameter("p_state"));
                String p_photo =request.getParameter("p_photo");
                //p_price,p_info,p_photo,p_state,p_t_id
                flag = new product_adminDao().admin_product(new Object[]{p_price,p_info,p_photo,p_state,t_id});
                if(flag){
                    response.getWriter().print("{\"msg\":\"添加成功，O(∩_∩)O\"}");
                }else{
                    response.getWriter().print("{\"msg\":\"添加失败!!!!\"}");
                }
                break;
            case "/p_admin_img":
                Img(request, response);
                break;
            case"/p_admin_type_name":
                String t_name = request.getParameter("t_name");
                flag = new product_adminDao().admin_type_add(new Object[]{t_name});
                if (flag){
                    response.getWriter().print("{\"msg\":\"添加成功，O(∩_∩)O\"}");
                }else{
                    response.getWriter().print("{\"msg\":\"该分类您已经添加啦，O(∩_∩)O\"}");
                }
                break;
            case "/p_admin_update":
                    int pid = Integer.parseInt(request.getParameter("p_id"));
                    String p_info1 =request.getParameter("p_info");
                    int t_id1=Integer.parseInt(request.getParameter("t_id"));
                    BigDecimal p_price1 = new BigDecimal(request.getParameter("p_price"));
                    int p_state1 = Integer.parseInt(request.getParameter("p_state"));
                    String p_photo1 =request.getParameter("p_photo");
                //p_price,p_info,p_photo,p_state,p_t_id
                    flag = new product_adminDao().admin_product_update(new Object[]{p_price1,p_info1,p_photo1,p_state1,t_id1,pid});
                    if(flag){
                        response.getWriter().print("{\"msg\":\"更新成功，O(∩_∩)O\"}");
                    }else{
                        response.getWriter().print("{\"msg\":\"更新失败，请联系管理员\"}");
                    }
                    break;
        }
    }

    private void Img(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 设置编码
        response.setCharacterEncoding("utf-8");
        // 物理路径
        String savePath = this.getServletConfig().getServletContext().getRealPath("");
        savePath = savePath + "image\\";
        File f1 = new File(savePath);
        if (!f1.exists()) {
            f1.mkdirs();
        }
        DiskFileItemFactory fac = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(fac);
        upload.setHeaderEncoding("utf-8");
        List<FileItem> fileList = null;
        try {
            fileList = upload.parseRequest(request);
        } catch (FileUploadException ex) {
            return;
        }

        Iterator<FileItem> it = fileList.iterator();
        String name = "";
        String extName = "";
        while (it.hasNext()) {
            FileItem item = it.next();
            if (!item.isFormField()) {
                name = item.getName();
                long size = item.getSize();
                String type = item.getContentType();
               // System.out.println(size + " " + type);
                if (name == null || name.trim().equals("")) {
                    continue;
                }

                // 扩展名格式：
                if (name.lastIndexOf(".") >= 0) {
                    extName = name.substring(name.lastIndexOf("."));
                }

                File file = null;
                do {
                    // 生成文件名：
                    name = UUID.randomUUID().toString();
                    file = new File(savePath + name + extName);
                } while (file.exists());

                File saveFile = new File(savePath + name + extName);
                try {
                    item.write(saveFile);
                } catch (Exception exp) {
                    response.getWriter().write(exp.getMessage());
                    exp.printStackTrace();
                }
            }
        }

        R r = new R();
        r.setCode(0);
        r.setMsg("上传成功");
        Map<String, String> data = new HashMap<String, String>();
        data.put("src", "image/" + name + extName);
        imgName=name+extName;
        data.put("name",name + extName);
        r.setData(data);
        response.getWriter().print(new Gson().toJson(r));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (request.getServletPath()){
            case "/p_admin_list":
                int page = Integer.parseInt(request.getParameter("page"));
                int limit = Integer.parseInt(request.getParameter("limit"));
                List<product_admin> list=null;
                try {
                    list = new product_adminDao().getAll(page,limit);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                int count = new product_adminDao().getCount();
                R r = new R(count,list);
                response.getWriter().print(new Gson().toJson(r));
                break;
            case "/p_type_list":
                try {
                    String list_type = new product_adminDao().getTypeName();
                    response.getWriter().print(list_type);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "/p_admin_state":
                int pid = Integer.parseInt(request.getParameter("p_id"));
                int state = Integer.parseInt(request.getParameter("p_state"));
                boolean flag = new product_adminDao().update_state(pid,state);
                if(flag){
                    response.getWriter().print("{\"msg\":\"状态修改成功，O(∩_∩)O\"}");
                }else{
                    response.getWriter().print("{\"msg\":\"状态修改失败，请联系管理员\"}");
                }
                break;
        }
    }
}
