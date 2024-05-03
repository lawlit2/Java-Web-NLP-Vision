package com.example.Controller;

import com.example.Server.AnimalOcrServer;
import com.example.entity.AnimalResultMessage;
import jakarta.annotation.Resource;
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
import java.util.List;

@Controller
public class AnimalReadController {
    @Resource
    AnimalOcrServer animalOcrServer;
    @GetMapping("/AnimalRead")
    public String GetAnimaRead(Model model){
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication().getPrincipal();
        model.addAttribute("nickname",user.getUsername());
        return  "AnimalRead";
    }
    @ResponseBody
    @PostMapping("/animal_identification")
    public List<AnimalResultMessage> PostAnimalRead(@RequestParam("file" ) MultipartFile file,Model model) throws IOException {
        String fileName = file.getOriginalFilename();
        String filepath = "C:\\Users\\ATRI\\IdeaProjects\\AIspringSSM\\src\\main\\resources\\static\\uploadFile\\";
        String RelativeName = "static/uploadFile/" + fileName;
        File dir = new File(filepath+fileName);
        file.transferTo(dir);
        List<AnimalResultMessage> list =animalOcrServer.ANIMAL_RESULT_MESSAGE(dir.getAbsolutePath(),fileName);
        return list;
    }
}
