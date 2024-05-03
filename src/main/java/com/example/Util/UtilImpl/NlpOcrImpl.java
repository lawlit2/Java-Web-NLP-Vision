package com.example.Util.UtilImpl;

import com.baidu.aip.nlp.AipNlp;
import com.example.Util.CreateUtil.CreatNlp;
import com.example.Util.NlpOcr;
import jakarta.annotation.Resource;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class NlpOcrImpl implements NlpOcr {
    @Resource
    CreatNlp creatNlp;
    @Override
    public JSONObject NlpRead(String text) {
        AipNlp aipNlp = creatNlp.CreateNlpUtil();
        HashMap<String,Object> options = new HashMap<>();
        return aipNlp.ecnet(text,options);
    }
}
