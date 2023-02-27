package servlet;
import dao.user_dao;
import entity.user;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class user_register_ extends  HttpServlet{
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws  ServletException,IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        user u = new user(name,password,email,1);

        user_dao dao = new user_dao();
        int count = dao.insert_user(u);
        PrintWriter out = response.getWriter();
        if(count >0 ) {
            out.print("<script type=\"text/javascript\">");
            out.print("alert('用户注册成功！请登录。');");
            out.print("location.href='login_in.jsp';");
            out.print("</script>");
        } else {
            out.write("<script type=\"text/javascript\">");
            out.write("alert('用户注册失败！请重新注册。');");
            out.write("location.href='register.jsp';");
            out.write("</script>");
        }
        out.close();
    }
}
