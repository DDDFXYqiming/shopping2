package dao;
import entity.product;
import util.jdbc;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class product_dao {
    public int selectByIdExist(int pid){
        int count=0;
        jdbc util=new jdbc();
        String sql="select * from product where PRODUCT_ID=?";
        PreparedStatement ps=util.getPs(sql);
        ResultSet rs=null;
        product pro=new product();
        try {
            ps.setInt(1, pid);
            rs=ps.executeQuery();
            if(rs.next()){
                pro.setPRODUCT_ID(rs.getInt(1));
                pro.setPRODUCT_NAME(rs.getString(2));
                pro.setPRODUCT_DESCRIPTION(rs.getString(3));
                pro.setPRODUCT_PRICE(rs.getInt(4));
                pro.setPRODUCT_STOCK(rs.getInt(5));
                count = 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close();
            util.close(ps,rs);
        }
        return count;
    }
    //更新商品的信息
    public void update_by_id(int productId, String name, String description, int price, int stock) {
        jdbc util = new jdbc();
        String sql = "update product set PRODUCT_NAME=?, PRODUCT_DESCRIPTION=?, PRODUCT_PRICE=?, PRODUCT_STOCK=? where PRODUCT_ID=?";
        PreparedStatement ps = util.getPs(sql);
        try {
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setInt(3, price);
            ps.setInt(4, stock);
            ps.setInt(5, productId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close();
            util.close(ps);
        }
    }

    public int insert(product product){
        int count = 0;
        jdbc util = new jdbc();
        String sql = "insert into product values (null, ?, ?, ?, ?)";
        PreparedStatement ps = util.getPs(sql);
        try{
            ps.setString(1, product.getPRODUCT_NAME());
            ps.setString(2, product.getPRODUCT_DESCRIPTION());
            ps.setInt(3, product.getPRODUCT_PRICE());
            ps.setInt(4, product.getPRODUCT_STOCK());

            count = ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close();
            util.close(ps);
        }
        return count;
    }


    public void delete_by_product_id(int id){
        jdbc util=new jdbc();
        String sql="delete from product where PRODUCT_ID=?";
        PreparedStatement ps=util.getPs(sql);
        try {
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close();
            util.close(ps);
        }
    }
    public List<product> selectAll() {
        jdbc util = new jdbc();
        String sql = "select * from product";
        PreparedStatement ps = util.getPs(sql);
        ResultSet rs = null;
        List<product> productList = new ArrayList<>();
        try {
            rs = ps.executeQuery();
            while (rs.next()) {
                product pro = new product();
                pro.setPRODUCT_ID(rs.getInt(1));
                pro.setPRODUCT_NAME(rs.getString(2));
                pro.setPRODUCT_DESCRIPTION(rs.getString(3));
                pro.setPRODUCT_PRICE(rs.getInt(4));
                pro.setPRODUCT_STOCK(rs.getInt(5));
                //pro.setPRODUCT_FILENAME(rs.getString(6));
                productList.add(pro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close();
            util.close(ps, rs);
        }
        return productList;
    }

    public product selectById(int pid){
        int id=pid;
        jdbc util=new jdbc();
        String sql="select * from product where PRODUCT_ID=?";
        PreparedStatement ps=util.getPs(sql);
        ResultSet rs=null;
        product pro=new product();
        try {
            ps.setInt(1,id);
            rs=ps.executeQuery();
            if(rs.next()){
                pro.setPRODUCT_ID(rs.getInt(1));
                pro.setPRODUCT_NAME(rs.getString(2));
                pro.setPRODUCT_DESCRIPTION(rs.getString(3));
                pro.setPRODUCT_PRICE(rs.getInt(4));
                pro.setPRODUCT_STOCK(rs.getInt(5));
//                pro.setPRODUCT_FILENAME(rs.getString(6));
                //Product pro=new Product(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close();
            util.close(ps,rs);
        }
        return pro;
    }

    public product selectByName(String pro_name){
        jdbc util=new jdbc();
        String sql="select * from product where PRODUCT_NAME like ?";
        PreparedStatement ps=util.getPs(sql);
        ResultSet rs=null;
        product pro=new product();
        try {
            ps.setString(1,'%'+pro_name+'%');
            rs=ps.executeQuery();
            if(rs.next()){
                pro.setPRODUCT_ID(rs.getInt(1));
                pro.setPRODUCT_NAME(rs.getString(2));
                pro.setPRODUCT_DESCRIPTION(rs.getString(3));
                pro.setPRODUCT_PRICE(rs.getInt(4));
                pro.setPRODUCT_STOCK(rs.getInt(5));
//                pro.setPRODUCT_FILENAME(rs.getString(6));
                //Product pro=new Product(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close();
            util.close(ps,rs);
        }
        return pro;
    }
    public List<product> selectAllByName(String pro_name) {
        jdbc util = new jdbc();
        String sql = "select * from product where PRODUCT_NAME like ?";
        PreparedStatement ps = util.getPs(sql);
        ResultSet rs = null;
        List<product> productList = new ArrayList<>();
        try {
            ps.setString(1, '%' + pro_name + '%');
            rs = ps.executeQuery();
            while (rs.next()) {
                product pro = new product();
                pro.setPRODUCT_ID(rs.getInt(1));
                pro.setPRODUCT_NAME(rs.getString(2));
                pro.setPRODUCT_DESCRIPTION(rs.getString(3));
                pro.setPRODUCT_PRICE(rs.getInt(4));
                pro.setPRODUCT_STOCK(rs.getInt(5));
                //pro.setPRODUCT_FILENAME(rs.getString(6));
                productList.add(pro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close();
            util.close(ps, rs);
        }
        return productList;
    }
//
//    public List<product> select_all_of() {
//        jdbc util = new jdbc();
//        String sql = "select * from product?";
//        PreparedStatement ps = util.getPs(sql);
//        ResultSet rs = null;
//        List<product> productList = new ArrayList<>();
//        try {
//            ps.setString(1, '');
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                product pro = new product();
//                pro.setPRODUCT_ID(rs.getInt(1));
//                pro.setPRODUCT_NAME(rs.getString(2));
//                pro.setPRODUCT_DESCRIPTION(rs.getString(3));
//                pro.setPRODUCT_PRICE(rs.getInt(4));
//                pro.setPRODUCT_STOCK(rs.getInt(5));
//                productList.add(pro);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            util.close();
//            util.close(ps, rs);
//        }
//        return allproductList;
//    }

}
