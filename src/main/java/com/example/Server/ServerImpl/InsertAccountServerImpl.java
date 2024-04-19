package com.example.Server.ServerImpl;

import com.example.Server.InsertAccountServer;
import com.example.entity.Account;
import com.example.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class InsertAccountServerImpl implements InsertAccountServer {
    @Resource
    UserMapper mapper;
    @Override
    public Boolean InsertAccount(String username, String password, String email) {
        if(mapper.InsertAccount(username,password,email)==1){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Boolean SelectAccount(String email) {
        Account account = mapper.SelectAccountByEmail(email);
        if(account==null){
            return false;
        }else{
            return true;
        }
    }
}
