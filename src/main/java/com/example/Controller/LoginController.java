package com.example.Controller;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
        @GetMapping("/login")
        public String login(){
            return "login";
        }
}
