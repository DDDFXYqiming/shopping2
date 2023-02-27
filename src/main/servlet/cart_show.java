package servlet;
import dao.product_dao;
import dao.cart_dao;
import entity.cart;
import entity.user;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class cart_show extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符集
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        HttpSession session=request.getSession(false);
        if (session!=null){
            //获取用户id
            user user=(user)session.getAttribute("user");
            int u_id=user.getId();

            //查询数据库
            cart_dao cartDao=new cart_dao();
            ArrayList<cart> cart_list=cartDao.select_all_product_by_user_id(u_id);

            product_dao pr_dao = new product_dao();

            cart cart=new cart();
            int flog=0;
//            for (cart cart_old : cart_list) {
//                System.out.println(pr_dao.selectById(cart_old.getCart_product_id()));
//                if(pr_dao.selectByIdExist(cart_old.getCart_product_id())==0){
//                    System.out.println("商品表没有该商品");
//                }
//            }
            Iterator<cart> iterator = cart_list.iterator();
            while (iterator.hasNext()) {
                cart cart_old = iterator.next();
                System.out.println(pr_dao.selectById(cart_old.getCart_product_id()));
                if(pr_dao.selectByIdExist(cart_old.getCart_product_id())==0){
                    System.out.println("商品表没有该商品");
                    iterator.remove();
                }
            }


            request.setAttribute("cart_list", cart_list);

            request.getRequestDispatcher("cart_main.jsp").forward(request, response);
        }else{
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alert('请先登录!');");
            out.write("location.href='login.jsp';");
            out.write("</script>");
            out.close();
            return;
        }
    }
}
