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
}
