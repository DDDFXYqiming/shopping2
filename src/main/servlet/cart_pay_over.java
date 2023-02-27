package servlet;

import dao.cart_dao;
import entity.cart;
import entity.user;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class cart_pay_over extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        HttpSession session=request.getSession();
        //获取用户id
        user user=(user)session.getAttribute("user");

        int u_id=user.getId();

        //查询数据库
        cart_dao cartDao=new cart_dao();
        ArrayList<cart> cart_list=cartDao.select_all_product_by_user_id(u_id);

        for (cart c : cart_list) {
            cartDao.update_cart_valid_by_cart_id(c.getCart_id());
        }

        PrintWriter out = response.getWriter();
        out.write("<script type=\"text/javascript\">");
        out.write("alert('购买成功！已发送邮件');");
        out.write("location.href='/shopping2_war/main_.jsp';");
        out.write("</script>");
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
