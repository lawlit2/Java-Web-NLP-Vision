package com.example.Util.CreateUtil.CreateUtilImpl;

import com.baidu.aip.imageclassify.AipImageClassify;
import com.example.Util.CreateUtil.CreatAnimalOcr;
import org.springframework.stereotype.Component;

@Component
public class CreatAnimalOcrImpl implements CreatAnimalOcr {
    public static final String APP_ID = "66397517";
    public static final String API_KEY = "nscoK5lwGQwH0Qc8VmMVnhrB";
    public static final String SECRET_KEY = "DSvr3zyZdv1nFwsuuqzcXdcffavCiLsN";
    @Override
    public AipImageClassify CreatAnimal() {
        AipImageClassify client = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        return client;
    }
}
