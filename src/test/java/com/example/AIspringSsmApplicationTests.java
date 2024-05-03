package com.example;

import com.example.Util.AirOcr;
import jakarta.annotation.Resource;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Random;

@SpringBootTest
class AIspringSsmApplicationTests {

    @Resource
    AirOcr airOcr;

    @Test
    void contextLoads() throws JSONException {
        JSONObject jsonObject = airOcr.OCRIdCard("C:\\Users\\ATRI\\IdeaProjects\\AIspringSSM\\src\\main\\resources\\uploadFile\\");
        System.out.println(jsonObject.toString());

    }
}
