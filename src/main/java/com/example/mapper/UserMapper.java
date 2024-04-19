package com.example.mapper;

import com.example.entity.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from account where username = #{username}")
    Account SelectAccountByUsername(String username);
    @Insert("insert into account(username,password,email)values(#{username},#{password},#{email})")
    int InsertAccount(String username,String password,String email);
    @Select("select * from account where email=#{email}")
    Account SelectAccountByEmail(String email);
    @Select("select * from account where password = #{password}")
    Account SelectAccountByPassword(String password);
}
