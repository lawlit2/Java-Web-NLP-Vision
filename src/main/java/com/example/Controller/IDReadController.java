package com.example.Controller;

import com.example.Server.IdMessageServer;
import com.example.Util.AirOcr;
import com.example.entity.IdMessage;
import jakarta.annotation.Resource;
import org.json.JSONObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class IDReadController {
    @Resource
    IdMessageServer idMessageServer;
    @Resource
    AirOcr airOcr;
    @GetMapping("/IdRead")
    public String IdRead(Model model){
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        model.addAttribute("nickname",user.getUsername());
        String username = user.getUsername();
        model.addAttribute("ListMessage",idMessageServer.SelectMessageByUsername(username));
        return "IDRead";
    }
    @ResponseBody
    @PostMapping("/upload")
    public IdMessage Upload(@RequestParam("file") MultipartFile file){
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String fileName = file.getOriginalFilename();
        String filePath = "C:\\Users\\ATRI\\IdeaProjects\\AIspringSSM\\src\\main\\resources\\static\\uploadFile";
        File dir = new File(filePath+fileName);
       try{
          IdMessage message;
           file.transferTo(dir);
           JSONObject jsonObject = airOcr.OCRIdCard(dir.getAbsolutePath());
            if(jsonObject.get("image_status").toString().equals("other_type_card")){
                return new IdMessage();
            }
         message = idMessageServer.InsertMessage(
                 ((JSONObject)((JSONObject)jsonObject.get("words_result")).get("姓名")).get("words").toString(),
                 ((JSONObject)((JSONObject)jsonObject.get("words_result")).get("性别")).get("words").toString(),
                 ((JSONObject)((JSONObject)jsonObject.get("words_result")).get("民族")).get("words").toString(),
                 Integer.parseInt(((JSONObject)((JSONObject)jsonObject.get("words_result")).get("出生")).get("words").toString()),
                 ((JSONObject)((JSONObject)jsonObject.get("words_result")).get("住址")).get("words").toString(),
                 ((JSONObject)((JSONObject)jsonObject.get("words_result")).get("公民身份号码")).get("words").toString(),
                    user.getUsername());
           return message;
       } catch (IOException e) {
           return new IdMessage();
       }
    }
}
