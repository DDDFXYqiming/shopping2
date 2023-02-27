package dao;
import entity.cart;
import  util.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class cart_dao {
    //查询特定用户已经卖出的商品
    public ArrayList<cart> select_cart_purchased_by_user_id(int uid){
        ArrayList<cart> list=new ArrayList<>();
        jdbc util=new jdbc();
        String sql = "select * from cart where cart_user_id=? and cart_valid=0 ";
        PreparedStatement ps=util.getPs(sql);
        ResultSet rs=null;
        try {
            ps.setInt(1,uid);
            rs=ps.executeQuery();
            while(rs.next()){
                cart cart=new cart(
                        rs.getInt("cart_id"),
                        rs.getString("cart_product_name"),
                        rs.getInt("cart_product_price"),
                        rs.getInt("cart_product_nums"),
                        rs.getInt("cart_product_stock"),
                        rs.getInt("cart_product_id"),
                        rs.getInt("cart_user_id"),
                        rs.getInt("cart_valid"),
                        rs.getString("created_at"),
                        rs.getString("updated_at")
                );
                list.add(cart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close();
            util.close(ps,rs);
        }
        return list;
    }
    //查询全部已经卖出的商品
    public ArrayList<cart> select_cart_valid_0(){
        ArrayList<cart> list=new ArrayList<>();
        jdbc util=new jdbc();
        String sql = "select * from cart where cart_valid=0 ";
        PreparedStatement ps=util.getPs(sql);
        ResultSet rs=null;
        try {
            rs=ps.executeQuery();
            while(rs.next()){
                cart cart=new cart(
                        rs.getInt("cart_id"),
                        rs.getString("cart_product_name"),
                        rs.getInt("cart_product_price"),
                        rs.getInt("cart_product_nums"),
                        rs.getInt("cart_product_stock"),
                        rs.getInt("cart_product_id"),
                        rs.getInt("cart_user_id"),
                        rs.getInt("cart_valid"),
                        rs.getString("created_at"),
                        rs.getString("updated_at")
                );
                int flog=0;
                for (cart cart_old : list) {
//                    list.update_cart_valid_by_cart_id(c.getCart_id());
                    if(cart_old.getCart_product_id()==cart.getCart_product_id()){
                        cart_old.setCart_product_nums(cart.getCart_product_nums()+cart_old.getCart_product_nums());
                        flog=1;
                    }
                }
                if(flog==0) {
                    list.add(cart);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close();
            util.close(ps,rs);
        }
        return list;
    }
    //查询全部尚未卖出的商品
    public ArrayList<cart> select_cart_valid_1(){
        ArrayList<cart> list=new ArrayList<>();
        jdbc util=new jdbc();
        String sql = "select * from cart where cart_valid=1 ";
        PreparedStatement ps=util.getPs(sql);
        ResultSet rs=null;
        try {
            rs=ps.executeQuery();
            while(rs.next()){
                cart cart=new cart(
                        rs.getInt("cart_id"),
                        rs.getString("cart_product_name"),
                        rs.getInt("cart_product_price"),
                        rs.getInt("cart_product_nums"),
                        rs.getInt("cart_product_stock"),
                        rs.getInt("cart_product_id"),
                        rs.getInt("cart_user_id"),
                        rs.getInt("cart_valid"),
                        rs.getString("created_at"),
                        rs.getString("updated_at")
                );
                int flog=0;
                for (cart cart_old : list) {
//                    list.update_cart_valid_by_cart_id(c.getCart_id());
                    if(cart_old.getCart_product_id()==cart.getCart_product_id()){
                        cart_old.setCart_product_nums(cart.getCart_product_nums()+cart_old.getCart_product_nums());
                        flog=1;
                    }
                }
                if(flog==0) {
                    list.add(cart);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close();
            util.close(ps,rs);
        }
        return list;
    }
    //购物车商品数量减一加一
    public void updateCartNums_add_and_sub(int count,int cid){
        jdbc util=new jdbc();
        String sql = "update cart set cart_product_nums=? where cart_id=? and cart_valid=1 ";
        PreparedStatement ps=util.getPs(sql);
        try {
            ps.setInt(1,count);
            ps.setInt(2,cid);
            ps.executeUpdate();
            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close();
            util.close(ps);
        }
    }
    //结算支付后修改购物车信息
    public void update_cart_valid_by_cart_id(int id){
        jdbc util=new jdbc();
        String sql = "update cart set cart_valid=? where cart_id=? and cart_valid=1 ";
        PreparedStatement ps=util.getPs(sql);
        try {
            ps.setInt(1,0);
            ps.setInt(2,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close();
            util.close(ps);
        }
    }
    public void delete_all_nums_by_cart_id(int id){
        jdbc util=new jdbc();
        String sql="delete from cart where cart_id=?";
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
    //更新购物车中的信息
    public void update_by_num(int temp, int count, int pid, int uid){
        jdbc util=new jdbc();
        String sql = "update cart set cart_product_nums=? where cart_user_id=? and cart_product_id=? and cart_valid=1 ";
        PreparedStatement ps=util.getPs(sql);
        try {
            ps.setInt(1,temp+count);
//            ps.setInt(2,1);
            ps.setInt(2,uid);
            ps.setInt(3,pid);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close();
            util.close(ps);
        }
    }

    //查询用户u_id的购物车中是否存在商品p_id
    public int select_by_uid_and_pid(int uid, int pid){
        int count=0;
        jdbc util=new jdbc();
        String sql="select cart_product_nums from cart where cart_user_id=? and cart_product_id=? and cart_valid=1 ";
        PreparedStatement ps=util.getPs(sql);
        ResultSet rs=null;
        try {
            ps.setInt(1,uid);
            ps.setInt(2,pid);
            rs=ps.executeQuery();
            if (rs.next()){
                count=rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close();
            util.close(ps,rs);
        }
        return count;
    }

    //加入用户购物车
    public int insert(cart cart){
        int count = 0;
        jdbc util = new jdbc();
        String sql = "insert into cart (cart_id,cart_product_name,cart_product_price, cart_product_nums,cart_product_stock,cart_product_id,cart_user_id, cart_valid) values (null, ?, ?, ?, ?, ?, ?, 1)";
        PreparedStatement ps = util.getPs(sql);
        try{
            ps.setString(1, cart.getCart_product_name());
            ps.setInt(2, cart.getCart_product_price());
            ps.setInt(3, cart.getCart_product_nums());
            ps.setInt(4, cart.getCart_product_stock());
            ps.setInt(5, cart.getCart_product_id());
            ps.setInt(6, cart.getCart_user_id());
            count = ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close();
            util.close(ps);
        }
        return count;
    }

    public List<cart> select_cart_All() {
        jdbc util = new jdbc();
        String sql = "select * from cart";
        PreparedStatement ps = util.getPs(sql);
        ResultSet rs = null;
        List<cart> all_cart_product_list = new ArrayList<>();
        try {
            rs=ps.executeQuery();
            while(rs.next()){
                cart cart=new cart(
                        rs.getInt("cart_id"),
                        rs.getString("cart_product_name"),
                        rs.getInt("cart_product_price"),
                        rs.getInt("cart_product_nums"),
                        rs.getInt("cart_product_stock"),
                        rs.getInt("cart_product_id"),
                        rs.getInt("cart_user_id"),
                        rs.getInt("cart_valid"),
                        rs.getString("created_at"),
                        rs.getString("updated_at")
                );
                all_cart_product_list.add(cart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close();
            util.close(ps,rs);
        }
        return all_cart_product_list;
    }

    public ArrayList<cart> select_all_product_by_user_id(int uid){
        ArrayList<cart> list=new ArrayList<>();
        jdbc util=new jdbc();
        String sql = "select * from cart where cart_user_id=? and cart_valid=1 ";
        PreparedStatement ps=util.getPs(sql);
        ResultSet rs=null;
        try {
            ps.setInt(1,uid);
            rs=ps.executeQuery();
            while(rs.next()){
                cart cart=new cart(
                        rs.getInt("cart_id"),
                        rs.getString("cart_product_name"),
                        rs.getInt("cart_product_price"),
                        rs.getInt("cart_product_nums"),
                        rs.getInt("cart_product_stock"),
                        rs.getInt("cart_product_id"),
                        rs.getInt("cart_user_id"),
                        rs.getInt("cart_valid"),
                        rs.getString("created_at"),
                        rs.getString("updated_at")
                );
                list.add(cart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close();
            util.close(ps,rs);
        }
        return list;
    }
}
