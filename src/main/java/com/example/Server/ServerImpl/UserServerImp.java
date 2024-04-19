package com.example.Server.ServerImpl;

import com.example.entity.Account;
import com.example.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServerImp implements UserDetailsService {
    @Resource
    UserMapper mapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = mapper.SelectAccountByUsername(username);
            if (account == null){
                throw new UsernameNotFoundException("用户名或密码未找到");
            }
        return User
                .withUsername(account.getUsername())
                .password(account.getPassword())
                .build();

    }
}
