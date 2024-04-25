package com.example.Server.ServerImpl;

import com.example.Server.AccountServer;
import com.example.entity.Account;
import com.example.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServerImpl implements AccountServer {
    @Resource
    BCryptPasswordEncoder encoder;
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



    @Override
    public boolean UpdatePassword(String email, String newPassword) {
       return mapper.UpdataAccountPassword(newPassword,email);
    }

}
