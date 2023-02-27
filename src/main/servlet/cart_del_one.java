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

public class cart_del_one extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符集
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        HttpSession session=request.getSession();

        int cid = Integer.parseInt(request.getParameter("cartId"));
        int nums = Integer.parseInt(request.getParameter("cartNums"));

        cart_dao cartDao=new cart_dao();

        System.out.println(cid);
        System.out.println(nums);
        if(nums-1==0){
            cartDao.delete_all_nums_by_cart_id(cid);
            //获取用户id
            user user=(user)session.getAttribute("user");
            int u_id=user.getId();

            //查询数据库
            ArrayList<cart> cart_list=cartDao.select_all_product_by_user_id(u_id);
            request.setAttribute("cart_list", cart_list);

            request.getRequestDispatcher("cart_main.jsp").forward(request, response);
        }
        cartDao.updateCartNums_add_and_sub(nums-1,cid);

        //获取用户id
        user user=(user)session.getAttribute("user");
        int u_id=user.getId();

        //查询数据库
        ArrayList<cart> cart_list=cartDao.select_all_product_by_user_id(u_id);
        request.setAttribute("cart_list", cart_list);

        request.getRequestDispatcher("cart_main.jsp").forward(request, response);
    }
}
