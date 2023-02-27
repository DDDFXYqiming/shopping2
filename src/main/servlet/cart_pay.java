package servlet;

import dao.cart_dao;
import entity.cart;
import entity.user;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class cart_pay extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符集
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        HttpSession session=request.getSession();
            //获取用户id
            user user=(user)session.getAttribute("user");

            int u_id=user.getId();
            System.out.println("结算用户ID"+u_id);
            //查询数据库
            cart_dao cartDao=new cart_dao();
            ArrayList<cart> cart_list=cartDao.select_all_product_by_user_id(u_id);

            System.out.println(cart_list.size());
            session.setAttribute("cart_list", cart_list);

            double totalPrice = 0.0;
            for (cart cart : cart_list) {
                totalPrice += cart.getCart_product_price() * cart.getCart_product_nums();
            }
            session.setAttribute("totalPrice",totalPrice);

            request.getRequestDispatcher("settlement.jsp").forward(request, response);
    }
}
