package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
    @Select("select * from notes where noteid = #{id}")
    Note findById(Integer id);

    @Select("select * from notes where userid = #{id}")
    List<Note> findByUser(Integer id);

    @Insert("insert into notes(notetitle, notedescription, userid) "
            + "values(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insert(Note note);

    @Update("update notes set notetitle=#{noteTitle}, notedescription=#{noteDescription}" +
            " where noteid = #{noteId}")
    int update(Note note);

    @Delete("delete from notes where noteid = #{noteId}")
    void delete(Integer id);

    @Select("select count(*) from notes")
    Integer count();
}

