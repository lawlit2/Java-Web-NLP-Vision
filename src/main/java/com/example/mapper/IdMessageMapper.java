package com.example.mapper;

import com.example.entity.IdMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IdMessageMapper {
    @Select("select name,Sex,Ethnic,Birth,location,IDNumber from idmessage where Username = #{username}")
    List<IdMessage> SelectMessageByUsername(String username);
    @Insert("insert into idmessage(name,Sex,Ethnic,Birth,location,IDNumber,Username)values(#{name},#{sex},#{Ethnic},#{Birth},#{location},#{IDNumber},#{username})")
    int InsertIdMessage(String name,String sex,String Ethnic,int Birth,String location,String IDNumber,String username);
}
