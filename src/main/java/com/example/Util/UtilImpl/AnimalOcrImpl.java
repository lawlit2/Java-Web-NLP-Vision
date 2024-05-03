package com.example.Util.UtilImpl;

import com.example.Util.AnimalOcr;
import com.example.Util.CreateUtil.CreatAnimalOcr;
import jakarta.annotation.Resource;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.HashMap;
@Component
public class AnimalOcrImpl implements AnimalOcr {
    @Resource
    CreatAnimalOcr creatAnimalOcr;
    @Override
    public JSONObject ReadAnimalCard(String path) {
        HashMap<String,String> options = new HashMap<>();
        options.put("top_num", "3");
        options.put("baike_num", "5");
        return creatAnimalOcr.CreatAnimal().animalDetect(path,options);
    }
}
