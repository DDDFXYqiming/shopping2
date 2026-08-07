package com.shopping.mapper;

import com.shopping.entity.Cart;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CartMapper {

    @Results(id = "cartMap", value = {
            @Result(column = "cart_id", property = "cartId"),
            @Result(column = "cart_product_id", property = "cartProductId"),
            @Result(column = "cart_product_name", property = "cartProductName"),
            @Result(column = "cart_product_price", property = "cartProductPrice"),
            @Result(column = "cart_product_nums", property = "cartProductNums"),
            @Result(column = "cart_product_stock", property = "cartProductStock"),
            @Result(column = "cart_user_id", property = "cartUserId"),
            @Result(column = "cart_valid", property = "cartValid"),
            @Result(column = "created_at", property = "createdAt"),
            @Result(column = "updated_at", property = "updatedAt")
    })
    @Select("SELECT * FROM cart WHERE cart_user_id = #{userId} AND cart_valid = 1 ORDER BY cart_id")
    List<Cart> selectValidByUserId(int userId);

    @ResultMap("cartMap")
    @Select("SELECT * FROM cart WHERE cart_user_id = #{userId} AND cart_valid = 0 ORDER BY cart_id")
    List<Cart> selectPurchasedByUserId(int userId);

    @ResultMap("cartMap")
    @Select("SELECT * FROM cart WHERE cart_valid = 0 ORDER BY cart_id")
    List<Cart> selectAllPurchased();

    @ResultMap("cartMap")
    @Select("SELECT * FROM cart ORDER BY cart_id")
    List<Cart> selectAll();

    @ResultMap("cartMap")
    @Select("SELECT * FROM cart WHERE cart_user_id = #{userId} AND cart_product_id = #{productId} AND cart_valid = 1")
    Cart selectByUserIdAndProductId(@Param("userId") int userId, @Param("productId") int productId);

    @ResultMap("cartMap")
    @Select("SELECT * FROM cart WHERE cart_id = #{cartId} AND cart_user_id = #{userId} AND cart_valid = 1")
    Cart selectByIdAndUserId(@Param("cartId") int cartId, @Param("userId") int userId);

    @Insert("INSERT INTO cart (cart_product_id, cart_product_name, cart_product_price, cart_product_nums, " +
            "cart_product_stock, cart_user_id, cart_valid) VALUES " +
            "(#{cartProductId}, #{cartProductName}, #{cartProductPrice}, #{cartProductNums}, " +
            "#{cartProductStock}, #{cartUserId}, 1)")
    @Options(useGeneratedKeys = true, keyProperty = "cartId")
    int insert(Cart cart);

    @Update("UPDATE cart SET cart_product_nums = #{nums}, updated_at = CURRENT_TIMESTAMP " +
            "WHERE cart_id = #{cartId} AND cart_valid = 1")
    int updateNums(@Param("cartId") int cartId, @Param("nums") int nums);

    @Update("UPDATE cart SET cart_valid = 0, updated_at = CURRENT_TIMESTAMP WHERE cart_id = #{cartId} AND cart_valid = 1")
    int updateValidToZero(int cartId);

    @Delete("DELETE FROM cart WHERE cart_id = #{cartId}")
    int deleteById(int cartId);
}
