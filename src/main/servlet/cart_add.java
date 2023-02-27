package servlet;
import dao.cart_dao;
import dao.product_dao;
import entity.cart;
import entity.product;
import entity.user;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class cart_add extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符集
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");



        int productId = Integer.parseInt(request.getParameter("productId"));
        String productName = request.getParameter("productName");
        int productPrice = Integer.parseInt(request.getParameter("productPrice"));
        int productStock = Integer.parseInt(request.getParameter("productStock"));

        cart cart = new cart();
        cart.setCart_product_id(productId);
        cart.setCart_product_name(productName);
        cart.setCart_product_price(productPrice);
        cart.setCart_product_stock(productStock);

        HttpSession session=request.getSession(false);
        String isLogin = (String) session.getAttribute("islogin");
        if (isLogin=="1"){
            //获取用户id
            user user=(user)session.getAttribute("user");
            int u_id=user.getId();
            System.out.println(u_id);
//            int u_id = Integer.getInteger(request.getParameter("userId"));
            //获取商品信息
            product_dao dao=new product_dao();
            product pro=dao.selectById(productId);
            //添加商品到购物车，有两规则
            //若用户u_id的购物车中已经存在商品p_id而且and cart_valid=1 ,则更新商品数量加上cout
            //若用户u_id的购物车中没有商品p_id,则插入数据到购物车
            cart_dao cartDao=new cart_dao();
            int temp=cartDao.select_by_uid_and_pid(u_id,productId);
            System.out.println("商品数量"+temp);
            if (temp>0){
//                更新购物车
                cartDao.update_by_num(temp,1,productId,u_id);
                //更新商品库存
                request.getRequestDispatcher("/main_.jsp").forward(request,response);

            }else{
//                if (1<=pro.getPRODUCT_STOCK()) {
                    cart cart2 = new cart(0,pro.getPRODUCT_NAME(), pro.getPRODUCT_PRICE(),1,pro.getPRODUCT_STOCK(), pro.getPRODUCT_ID(),  u_id, 1,null,null);
                    //插入购物车
                    cartDao.insert(cart2);
                    //更新商品库存
                    request.getRequestDispatcher("/main_.jsp").forward(request,response);
//
//                }else{
//                    PrintWriter out = response.getWriter();
//                    out.write("<script>");
//                    out.write("alert('库存不足，请重新选择');");
//                    out.write("location.href='main_.jsp'");
//                    out.write("</script>");
//                    out.close();
//                    return;
//                }
            }
        }else{
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alert('请先登录后，再购买');");
            out.write("location.href='login_in.jsp';");
            out.write("</script>");
            out.close();
            return;
        }

        response.sendRedirect("/shopping2_war/main_.jsp");
    }
}