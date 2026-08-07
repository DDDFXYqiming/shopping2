package com.shopping.mapper;

import com.shopping.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Results(id = "productMap", value = {
            @Result(column = "PRODUCT_ID", property = "productId"),
            @Result(column = "PRODUCT_NAME", property = "productName"),
            @Result(column = "PRODUCT_DESCRIPTION", property = "productDescription"),
            @Result(column = "PRODUCT_PRICE", property = "productPrice"),
            @Result(column = "PRODUCT_STOCK", property = "productStock")
    })
    @Select("SELECT * FROM product")
    List<Product> selectAll();

    @ResultMap("productMap")
    @Select("SELECT * FROM product WHERE PRODUCT_ID = #{id}")
    Product selectById(int id);

    @ResultMap("productMap")
    @Select("SELECT * FROM product WHERE PRODUCT_NAME LIKE CONCAT('%', #{keyword}, '%')")
    List<Product> selectByNameLike(String keyword);

    @Insert("INSERT INTO product (PRODUCT_NAME, PRODUCT_DESCRIPTION, PRODUCT_PRICE, PRODUCT_STOCK) " +
            "VALUES (#{productName}, #{productDescription}, #{productPrice}, #{productStock})")
    @Options(useGeneratedKeys = true, keyProperty = "productId")
    int insert(Product product);

    @Update("UPDATE product SET PRODUCT_NAME=#{productName}, PRODUCT_DESCRIPTION=#{productDescription}, " +
            "PRODUCT_PRICE=#{productPrice}, PRODUCT_STOCK=#{productStock} WHERE PRODUCT_ID=#{productId}")
    int update(Product product);

    @Delete("DELETE FROM product WHERE PRODUCT_ID = #{id}")
    int deleteById(int id);
}
