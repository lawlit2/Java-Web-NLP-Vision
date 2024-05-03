package com.example;

import com.example.Server.AnimalOcrServer;
import com.example.Server.IdMessageServer;
import com.example.Server.NlpOcrServer;
import com.example.Util.AirOcr;
import com.example.Util.AnimalOcr;
import com.example.Util.NlpOcr;
import com.example.entity.AnimalResultMessage;
import com.example.entity.DriverLicence;
import com.example.entity.NlpTextMessage;
import jakarta.annotation.Resource;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
class AIspringSsmApplicationTests {

    @Resource
    NlpOcrServer server;

    @Test
    void contextLoads() throws JSONException {
        String date = "2018-23";
        List<NlpTextMessage> list = server.NLP_TEXT_MESSAGE("百度是一家至能公司", date);
        for (NlpTextMessage nlpTextMessage : list) {
            System.out.println(nlpTextMessage.toString());
        }
    }
}
