package com.example.Server;

import com.example.entity.Account;

public interface InsertAccountServer {
    Boolean InsertAccount(String username,String password,String email);
    Boolean SelectAccount(String email);
}
