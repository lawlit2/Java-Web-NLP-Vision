package com.example.Server.ServerImpl;

import com.example.Server.IdMessageServer;
import com.example.entity.IdMessage;
import com.example.mapper.IdMessageMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdMessageServerImpl implements IdMessageServer {
    @Resource
    IdMessageMapper mapper;
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
}
