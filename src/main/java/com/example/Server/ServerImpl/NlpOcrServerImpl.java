package com.example.Server.ServerImpl;

import com.example.Server.NlpOcrServer;
import com.example.Util.NlpOcr;
import com.example.entity.NlpTextMessage;
import jakarta.annotation.Resource;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class NlpOcrServerImpl implements NlpOcrServer {
    @Resource
    NlpOcr nlpOcr;

    @Override
    public List<NlpTextMessage> NLP_TEXT_MESSAGE(String text,String data) {
      JSONObject jsonObject = nlpOcr.NlpRead(text);
      String o = (String) ((JSONObject) jsonObject.get("item")).get("correct_query");
        List<NlpTextMessage> list = new ArrayList<>();
        NlpTextMessage message1 =new NlpTextMessage();
        message1.setDate(data);
        message1.setMessage(o);
        message1.setType("incoming");
        NlpTextMessage message2 = new NlpTextMessage();
        message2.setType("outgoing");
        message2.setDate(data);
        message2.setMessage(text);
        list.add(message1);
        list.add(message2);
        return list;
    }
}
