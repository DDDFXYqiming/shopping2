package servlet;
import dao.user_dao;
import entity.user;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet("/login_")
public class user_login_ extends HttpServlet{
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String name = request.getParameter("username");
        String password = request.getParameter("password");

        user_dao dao = new user_dao();
        int count = dao.login(name,password);
        if(count>0){
            HttpSession session = request.getSession();
            user u=dao.selectuser_id(name);

            session.setAttribute("user",u);

            System.out.println("登陆用户id："+u.getId()+"  u.status="+u.getStatus());

            session.setAttribute("islogin","1");

//            request.getRequestDispatcher("main_.jsp").forward(request,response);
            response.sendRedirect("/shopping2_war/main_.jsp");
        }else{
            PrintWriter out = response.getWriter();
            out.write("<script type=\"text/javascript\">");
            out.write("alert('用户登录失败！请重新登录');");
            out.write("location.href='/shopping2_war/login_in.jsp';");
            out.write("</script>");
            out.close();
        }
    }
}
