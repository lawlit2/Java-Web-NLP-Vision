package com.example.Util.Impl;

import com.baidu.aip.ocr.AipOcr;
import com.example.Util.CreatAipOcr;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class CreatAipOcrImpl implements CreatAipOcr {
    public static final String APP_ID = "64387261";
    public static final String API_KEY = "P7w1labwHymHzfAHDxohnWwx";
    public static final String SECRET_KEY = "6JpL2DdVuDiQ7ivLYMlON0rIFeiXylaM";
    @Override
    public AipOcr CreatAipOcr() {
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        return client;
    }
}
