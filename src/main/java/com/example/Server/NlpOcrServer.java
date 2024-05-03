package com.example.Server;

import com.example.entity.NlpTextMessage;

import java.util.List;

public interface NlpOcrServer {
    List<NlpTextMessage> NLP_TEXT_MESSAGE(String Message, String Date);
}
