package com.example.Server;

import com.example.entity.AnimalResultMessage;

import java.util.List;

public interface AnimalOcrServer {
    List<AnimalResultMessage> ANIMAL_RESULT_MESSAGE(String path,String relative_path);
}
