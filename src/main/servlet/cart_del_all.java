package servlet;
import dao.cart_dao;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class cart_del_all extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pid = request.getParameter("pid");

        cart_dao cartDao=new cart_dao();
        cartDao.delete_all_nums_by_cart_id(Integer.parseInt(pid));
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
