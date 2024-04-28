package com.example.Util.Impl;

import com.example.Util.AirOcr;
import com.example.Util.CreatAipOcr;
import jakarta.annotation.Resource;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Component
public class AirOcrImpl implements AirOcr {
    @Resource
    CreatAipOcr CreatClient;
    @Override
    public JSONObject OCRIdCard(String path) {
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("detect_direction", "true");
        options.put("detect_risk", "false");
        String idCardSide = "back";
        // 参数为本地图片路径
        JSONObject res = CreatClient.CreatAipOcr().idcard(path, idCardSide, options);
        return res;
    }
}
