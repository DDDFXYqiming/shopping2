package servlet;

import dao.product_dao;
import dao.cart_dao;
import entity.cart;
import entity.product;
import entity.user;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class cart_purchased_records_by_user_id extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession(false);

        user user=(user)session.getAttribute("user");
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符集
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        int uid = Integer.parseInt(request.getParameter("searchText"));

        System.out.println("购买记录查询用户："+uid);

        cart_dao cartDao=new cart_dao();
        product_dao pr_dao=new product_dao();

        ArrayList<cart> cart_list0=cartDao.select_cart_valid_0();

        Iterator<cart> iterator = cart_list0.iterator();
        while (iterator.hasNext()) {
            cart cart_old = iterator.next();
            if(cart_old.getCart_user_id()!=uid){
                System.out.println("已完成用户筛选");
                iterator.remove();
            }
        }

        request.setAttribute("cart_list0", cart_list0);

        ArrayList<cart> cart_list1=cartDao.select_cart_valid_1();

        request.setAttribute("cart_list1", cart_list1);

        request.getRequestDispatcher("cart_already_been_purchased_records.jsp").forward(request, response);

    }
}
