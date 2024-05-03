package com.example.Util.CreateUtil.CreateUtilImpl;

import com.baidu.aip.nlp.AipNlp;
import com.example.Util.CreateUtil.CreatNlp;
import org.springframework.stereotype.Component;

@Component
public class CreatNlpImpl implements CreatNlp {
    public static final String APP_ID = "64387261";
    public static final String API_KEY = "P7w1labwHymHzfAHDxohnWwx";
    public static final String SECRET_KEY = "6JpL2DdVuDiQ7ivLYMlON0rIFeiXylaM";
    @Override
    public AipNlp CreateNlpUtil() {
        AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        return client;
    }
}
