package com.example.Controller;

import com.example.Server.IdMessageServer;
import com.example.Util.AirOcr;
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
    public String  Upload(@RequestParam("file") MultipartFile file){
        if(file.isEmpty()){
            return "上传失败,请选择文件";
        }
        String fileName = file.getOriginalFilename();
        String filePath = "C:\\Users\\ATRI\\IdeaProjects\\AIspringSSM\\src\\main\\resources\\uploadFile\\";
        File dir = new File(filePath+fileName);
       try{
           file.transferTo(dir);
           return "文件上传成功";
       } catch (IOException e) {
           return "文件上传失败";
       }
    }
}
