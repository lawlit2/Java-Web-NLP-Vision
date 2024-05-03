package com.example.Util.UtilImpl;

import com.example.Util.AirOcr;
import com.example.Util.CreateUtil.CreatAipOcr;
import jakarta.annotation.Resource;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

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

    @Override
    public JSONObject DriverLicenceOcr(String path) {
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("detect_direction", "true");
        JSONObject jsonObject = CreatClient.CreatAipOcr().drivingLicense(path,options);
        return jsonObject;
    }

}
