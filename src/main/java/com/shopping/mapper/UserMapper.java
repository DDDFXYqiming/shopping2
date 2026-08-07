package com.shopping.mapper;

import com.shopping.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM `user` WHERE name = #{name}")
    User selectByName(String name);

    @Select("SELECT * FROM `user` WHERE id = #{id}")
    User selectById(int id);

    @Insert("INSERT INTO `user` (name, password, email, status) VALUES (#{name}, #{password}, #{email}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User user);

    @Update("UPDATE `user` SET password = #{password} WHERE name = #{name}")
    int updatePasswordByName(@Param("name") String name, @Param("password") String password);
}
