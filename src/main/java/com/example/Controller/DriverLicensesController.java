package com.example.Controller;

import com.example.Server.IdMessageServer;
import com.example.entity.DriverLicence;
import jakarta.annotation.Resource;
import org.springframework.boot.Banner;
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
public class DriverLicensesController {
    @Resource
    IdMessageServer idMessageServer;
    @GetMapping("/DriverLicenses")
    public String getPage(Model model){
        User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("nickname",user.getUsername());
        model.addAttribute("list_driver",idMessageServer.SelectDriverLicence(user.getUsername()));
        return "driver_license_Read";
    }
    @PostMapping("/DriverLicensesOcr")
    @ResponseBody
    public DriverLicence OcrDriverLicenses(@RequestParam("file")MultipartFile file){
        User user =(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username  = user.getUsername();
        String filename = file.getOriginalFilename();
        String filePath = "C:\\Users\\ATRI\\IdeaProjects\\AIspringSSM\\src\\main\\resources\\static\\uploadFile";
        File dir = new File(filePath+filename);
        try {
            file.transferTo(dir);
            DriverLicence driverLicence = idMessageServer.InsertMessage(dir.getAbsolutePath(),username);
            return driverLicence;
        } catch (IOException e) {
            e.printStackTrace();
            return new DriverLicence();
        }
    }
}
