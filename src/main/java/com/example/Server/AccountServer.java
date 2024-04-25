package com.example.Server;

public interface AccountServer {
    Boolean InsertAccount(String username,String password,String email);
    Boolean SelectAccount(String email);
    boolean UpdatePassword(String email, String newPassword);

}
