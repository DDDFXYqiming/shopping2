package dao;
import entity.cart;
import entity.user;
import util.jdbc;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class user_dao {

    public int login(String name,String password){
        int count = 0;
        jdbc util = new jdbc();
        String sql="select * from user where name=? and password=?";
        PreparedStatement ps = util.getPs(sql);
        ResultSet rs = null;
        try{
            ps.setString(1,name);
            ps.setString(2,password);
            rs=ps.executeQuery();
            if(rs.next()){
                count+=1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close();
            util.close(ps,rs);
        }
        return count;
    }
    public user selectuser_id(String username){
        user user=new user();
        jdbc util = new jdbc();
        String sql = "select * from user where name=?";

        PreparedStatement ps=util.getPs(sql);
        ResultSet rs=null;
        try {
            ps.setString(1,username);
            rs=ps.executeQuery();
            if (rs.next()){
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setStatus(rs.getInt("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
    }finally {
        util.close();
        util.close(ps,rs);
        }
        return user;
    }
    public int insert_user(user u){
        int count = 0;
        jdbc util = new jdbc();
        String sql = "insert into user (name,password,email,status) values(?,?,?,?)";
        PreparedStatement ps = util.getPs(sql);
        ResultSet rs = null;
        try{
            ps.setString(1,u.getName());
            ps.setString(2,u.getPassword());
            ps.setString(3,u.getEmail());
            ps.setInt(4,u.getStatus());
            count=ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            util.close();
            util.close(ps,rs);
        }
        return count;
    }
}
