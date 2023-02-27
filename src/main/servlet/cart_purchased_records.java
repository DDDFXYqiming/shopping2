package servlet;

import dao.cart_dao;
import entity.cart;
import entity.user;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class cart_purchased_records extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession(false);

        user user=(user)session.getAttribute("user");
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符集
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        cart_dao cartDao=new cart_dao();

        ArrayList<cart> cart_list0=cartDao.select_cart_valid_0();

        request.setAttribute("cart_list0", cart_list0);

        ArrayList<cart> cart_list1=cartDao.select_cart_valid_1();

        request.setAttribute("cart_list1", cart_list1);

        request.getRequestDispatcher("cart_already_been_purchased_records.jsp").forward(request, response);

    }
}
