package com.example.Controller;

import com.example.Server.NlpOcrServer;
import com.example.entity.NlpTextMessage;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Param;
import org.json.JSONObject;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class NlpController {
    @Resource
    NlpOcrServer server;
    @GetMapping("/NLPPage")
    public String GetNlp(){
        return "NLp";
    }
    @PostMapping("/Nlp")
    @ResponseBody
    public List<NlpTextMessage> PostNlp(@RequestBody String requestBody){
        JSONObject message = new JSONObject(requestBody);
        String OldMessage = (String) message.get("content");
        String data = (String) message.get("time");
        List<NlpTextMessage> nlpTextMessage = server.NLP_TEXT_MESSAGE(OldMessage, data);
        return nlpTextMessage;
    }
}
