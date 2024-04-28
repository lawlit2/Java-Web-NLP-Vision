package com.example.mapper;

import com.example.entity.IdMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IdMessageMapper {
    @Select("select name,Sex,Ethnic,Birth,location,IDNumber,IssuAuthor,DateOfIssue from idmessage where Username = #{username}")
    List<IdMessage> SelectMessageByUsername(String username);

}
