package com.example.mapper;

import com.example.entity.DriverLicence;
import com.example.entity.IdMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.mail.SimpleMailMessage;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Mapper
public interface IdMessageMapper {
    @Select("select name,Sex,Ethnic,Birth,location,IDNumber from idmessage where Username = #{username}")
    List<IdMessage> SelectMessageByUsername(String username);
    @Insert("insert into idmessage(name,Sex,Ethnic,Birth,location,IDNumber,Username)values(#{name},#{sex},#{Ethnic},#{Birth},#{location},#{IDNumber},#{username})")
    int InsertIdMessage(String name,String sex,String Ethnic,int Birth,String location,String IDNumber,String username);
    @Insert("insert into driverlicence(IdCard,date,ApprovedDrivingType, ValidDate,name,FirstDate,Username)values(#{IdCard},#{date},#{ApprovedDrivingType},#{ValidDate},#{name},#{FirstDate},#{username})")
    int InsertDriverLicenceMessage(String IdCard, Date date,String ApprovedDrivingType,Date ValidDate,String name,Date FirstDate,String username);

    @Select("select IdCard,date,ApprovedDrivingType,ValidDate,name,FirstDate from driverlicence where Username = #{username}")
    List<DriverLicence> SelectDriverLicense(String username);
    default Date convertStringToDate(String dateStr){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        try {
            return format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
