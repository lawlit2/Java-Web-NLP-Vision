package com.example.Server;

import com.example.entity.IdMessage;

import java.util.List;

public interface IdMessageServer {
    public List<IdMessage> SelectMessageByUsername(String Username);
}
