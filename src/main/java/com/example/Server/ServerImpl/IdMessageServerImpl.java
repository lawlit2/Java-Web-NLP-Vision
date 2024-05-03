package com.example.Server.ServerImpl;

import com.baidu.aip.ocr.AipOcr;
import com.example.Server.IdMessageServer;
import com.example.Util.AirOcr;
import com.example.entity.DriverLicence;
import com.example.entity.IdMessage;
import com.example.mapper.IdMessageMapper;
import jakarta.annotation.Resource;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class IdMessageServerImpl implements IdMessageServer {
    @Resource
    IdMessageMapper mapper;
    @Resource
    AirOcr airOcr;
    @Override
    public List<IdMessage> SelectMessageByUsername(String Username) {
        return mapper.SelectMessageByUsername(Username);
    }

    @Override
    public IdMessage InsertMessage(String name, String sex, String Ethnic, int Birth, String location, String IDNumber, String username) {
        IdMessage idMessage = new IdMessage();
        idMessage.setName(name);
        idMessage.setSex(sex);
        idMessage.setEthnic(Ethnic);
        idMessage.setBirth(Birth);
        idMessage.setLocation(location);
        idMessage.setIDNumber(IDNumber);
        int number = mapper.InsertIdMessage(name, sex, Ethnic,  Birth,  location, IDNumber,username);
        if(number==1){
            return idMessage;
        }else{
            return null;
        }
    }

    @Override
    public DriverLicence InsertMessage(String path, String Username) {
        JSONObject jsonObject = airOcr.DriverLicenceOcr(path);
        DriverLicence driverLicence = new DriverLicence();
        if(jsonObject.has("error_code")){
            return null;
        }
        String IDCard = (String)((JSONObject) ((JSONObject)((JSONObject)jsonObject).get("words_result")).get("证号")).get("words");
        String StringDate = (String)((JSONObject)((JSONObject)((JSONObject)jsonObject).get("words_result")).get("有效期限")).get("words");
        String ApprovedDrivingType = (String) ((JSONObject)((JSONObject)((JSONObject)jsonObject).get("words_result")).get("准驾车型")).get("words");
        String StringValidDate = (String) ((JSONObject)((JSONObject)((JSONObject)jsonObject).get("words_result")).get("至")).get("words");
        String name = (String) ((JSONObject)((JSONObject)((JSONObject)jsonObject).get("words_result")).get("姓名")).get("words");
        String StringFirstDate = (String) ((JSONObject)((JSONObject)((JSONObject)jsonObject).get("words_result")).get("初次领证日期")).get("words");
        Date date =mapper.convertStringToDate(StringDate);
        Date ValidDate = mapper.convertStringToDate(StringValidDate);
        Date FirstDate = mapper.convertStringToDate(StringFirstDate);
        mapper.InsertDriverLicenceMessage(IDCard,date,ApprovedDrivingType,ValidDate,name,FirstDate,Username);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        driverLicence.setIdCard(IDCard);
        driverLicence.setDate(simpleDateFormat.format(date));
        driverLicence.setFirstDate(simpleDateFormat.format(FirstDate));
        driverLicence.setValidDate(simpleDateFormat.format(ValidDate));
        driverLicence.setApprovedDrivingType(ApprovedDrivingType);
        driverLicence.setName(name);
        return driverLicence;
    }
    public List<DriverLicence> SelectDriverLicence(String username){
        return mapper.SelectDriverLicense(username);
    }
}
