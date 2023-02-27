package servlet;

import dao.product_dao;
import entity.product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class product_delete extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符集
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        int productId = Integer.parseInt(request.getParameter("productId"));

        product_dao pr_dao = new product_dao();
        pr_dao.delete_by_product_id(productId);

        List<product> productList = new ArrayList<>();

        List<product> allProductList = pr_dao.selectAll(); // 获取所有商品的列表
        request.setAttribute("allProductList", allProductList); // 将所有商品的列表放入request中
        request.setAttribute("productList", productList);
        request.getRequestDispatcher("/main_.jsp").forward(request, response);
    }
}
