package servlet;
import dao.product_dao;
import dao.user_dao;
import entity.product;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class product_show extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        product_dao dao = new product_dao();
        List<product> allProductList = dao.selectAll(); // 获取所有商品的列表
        request.setAttribute("allProductList", allProductList); // 将所有商品的列表放入request中
        System.out.println("allProductList size: " + allProductList.size());
        request.getRequestDispatcher("/main_.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String searchText = request.getParameter("searchText");

        product_dao dao = new product_dao();

        List<product> productList_old = dao.selectAllByName(searchText);
        HttpSession session = request.getSession();
        session.setAttribute("productList_old", productList_old);
//        request.getRequestDispatcher("/product_list.jsp").forward(request, response);

        List<product> productList = new ArrayList<>();

        List<product> allProductList = dao.selectAll(); // 获取所有商品的列表
        request.setAttribute("allProductList", allProductList); // 将所有商品的列表放入request中

        if(searchText != null && !searchText.isEmpty()){
            List<product> products = dao.selectAllByName(searchText);
            for (product pro : products) {
                productList.add(pro);
            }
            request.setAttribute("searchText", searchText);
        } else {
            productList.addAll(allProductList); // 添加所有产品到productList中
        }
        if(productList.size() == 0){
            PrintWriter out = response.getWriter();
            out.write("<script type=\"text/javascript\">");
            out.write("alert('没有这个商品');");
            out.write("location.href='/shopping2_war/main_.jsp';");
            out.write("</script>");
            out.close();
        }

        System.out.println("allProductList size: " + allProductList.size());
        System.out.println("productList size: " + productList.size());

        request.setAttribute("productList", productList);
        request.getRequestDispatcher("/main_.jsp").forward(request, response);
    }

}
