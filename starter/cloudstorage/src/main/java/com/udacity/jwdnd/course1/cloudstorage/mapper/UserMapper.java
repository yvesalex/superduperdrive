package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("select * from users where userid = #{id}")
    User findById(Integer id);

    @Select("select * from users where username = #{username}")
    User getUser(String username);

    @Insert("insert into users(username, salt, firstname, lastname, password) "
            + "values(#{username}, #{salt}, #{firstname}, #{lastname}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insert(User user);

    @Delete("delete from users where userid = #{id}")
    void delete(Integer id);

    @Select("select count(*) from users")
    Integer count();
}

