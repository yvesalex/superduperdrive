package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {
    @Select("select * from credentials where credentialid = #{id}")
    Credential findById(Integer id);

    @Select("select * from credentials where userid = #{id}")
    List<Credential> findByUser(Integer id);

    @Insert("insert into credentials(url, username, key, password, userid) "
            + "values(#{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insert(Credential credential);

    @Update("update credentials set url=#{url}, username=#{username}, " +
            " key=#{key}, password=#{password}" +
            " where credentialid = #{credentialId}")
    int update(Credential credential);

    @Delete("delete from credentials where credentialid = #{credentialId}")
    void delete(Integer id);

    @Select("select count(*) from credentials")
    Integer count();
}

