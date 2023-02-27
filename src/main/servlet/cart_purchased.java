package servlet;

import dao.cart_dao;
import entity.cart;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class cart_purchased extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符集
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

//        HttpSession session=request.getSession(false);
//        //获取用户id
//        user user=(user)session.getAttribute("user");
//        int u_id=user.getId();

        //查询数据库
        cart_dao cartDao=new cart_dao();
        ArrayList<cart> cart_list=cartDao.select_cart_valid_0();

        request.setAttribute("cart_list", cart_list);

        request.getRequestDispatcher("cart_already_been_purchased.jsp").forward(request, response);
    }
}
