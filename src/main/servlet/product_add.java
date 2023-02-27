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

public class product_add extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符集
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String productName = request.getParameter("PRODUCT_NAME");
        String productDescription = request.getParameter("PRODUCT_DESCRIPTION");
        int productPrice = Integer.parseInt(request.getParameter("PRODUCT_PRICE"));
        int productStock = Integer.parseInt(request.getParameter("PRODUCT_STOCK"));

        product product_in = new product();
        product_in.setPRODUCT_NAME(productName);
        product_in.setPRODUCT_DESCRIPTION(productDescription);
        product_in.setPRODUCT_PRICE(productPrice);
        product_in.setPRODUCT_STOCK(productStock);

        product_dao pr_dao = new product_dao();
        pr_dao.insert(product_in);

        List<product> productList = new ArrayList<>();

        List<product> allProductList = pr_dao.selectAll(); // 获取所有商品的列表
        request.setAttribute("allProductList", allProductList); // 将所有商品的列表放入request中
        request.setAttribute("productList", productList);

        request.getRequestDispatcher("/main_.jsp").forward(request, response);
    }
}
