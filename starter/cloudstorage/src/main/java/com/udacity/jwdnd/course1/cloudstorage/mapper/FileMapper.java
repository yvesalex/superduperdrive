package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("select * from files where fileid = #{id}")
    File findById(Integer id);

    @Select("select * from files where userid = #{id}")
    List<File> findByUser(Integer id);

    @Insert("insert into files(filename, contenttype, filesize, userid, filedata) "
            + "values(#{filename}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(File file);

    @Update("update files set filename=#{filename}, contentType=#{contentType}," +
            " filesize=#{fileSize}, filedata=#{fileData}" +
            " where fileid = #{fileId}")
    int update(File file);

    @Delete("delete from files where fileid = #{fileId}")
    void delete(Integer id);

    @Select("select count(*) from files")
    Integer count();
}

