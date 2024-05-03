package com.example.Server.ServerImpl;

import com.example.Server.AnimalOcrServer;
import com.example.Server.IdMessageServer;
import com.example.Util.AnimalOcr;
import com.example.entity.AnimalResultMessage;
import jakarta.annotation.Resource;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalOcrServerImpl implements AnimalOcrServer {

    @Resource
    AnimalOcr animalOcr;
    @Override
    public List<AnimalResultMessage> ANIMAL_RESULT_MESSAGE(String path,String relative_path) {
        JSONObject jsonObject = animalOcr.ReadAnimalCard(path);
        JSONArray jsonArray = jsonObject.getJSONArray("result");
        List<AnimalResultMessage> list = new ArrayList<>();
        for (int i = 0;i <jsonArray.length();i++){
            AnimalResultMessage animalResultMessage = new AnimalResultMessage();
            animalResultMessage.setName((String) ((JSONObject)jsonArray.get(i)).get("name"));
            animalResultMessage.setScore(Double.parseDouble((String) ((JSONObject)jsonArray.get(i)).get("score")));
            animalResultMessage.setInformation((String) ((JSONObject)((JSONObject)jsonArray.get(i)).get("baike_info")).get("description"));
            animalResultMessage.setPath(relative_path);
            list.add(animalResultMessage);
        }

        return list;
    }


}
