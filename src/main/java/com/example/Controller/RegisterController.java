package com.example.Controller;

import com.example.Server.InsertAccountServer;
import com.example.Util.MessageSender;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegisterController {
    @Resource
    RedisTemplate<Object,Object> redisTemplate;
    @Resource
    MessageSender messageSender;
    @Resource
    InsertAccountServer insertAccountServer;
    @Resource
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @GetMapping("/register")
    public String register(){
       return "register";
    }
    @ResponseBody
    @PostMapping("/SendMessage")
    public String send_message(@RequestParam String email){
        messageSender.SendMessage(email);
        return "验证码发送成功";
    }
    @ResponseBody
    @PostMapping("/register1")
    public String register1(@RequestParam String emailAddress ,
                            @RequestParam String username,
                            @RequestParam String password,
                            @RequestParam int VerificationCode){

        password = bCryptPasswordEncoder.encode(password);

        Integer VCode = (Integer) redisTemplate.opsForValue().get(emailAddress);
        if(VCode==null) return "请重新发送验证码";
        if(!VCode.equals(VerificationCode)) return "验证码不正确";
        if(insertAccountServer.InsertAccount(username,password,emailAddress)){
            return "注册成功";
        }else{
            return "重复注册";
        }
    }
}
