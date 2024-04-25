package com.example.Controller;

import com.example.Server.AccountServer;
import com.example.Util.MessageSender;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ForgetPasswordController {
    @Resource
    RedisTemplate<Object,Object> redisTemplate;
    @Resource
    MessageSender sender;
    @Resource
    AccountServer server;
    @GetMapping("/forgot_password")
    public String forget_Password(){
        return "forgot-pws";
    }
    @ResponseBody
    @PostMapping("/send_code")
    public String send_code(@RequestParam String email){
       if( server.SelectAccount(email)) {
           sender.SendMessage(email);
           return "验证码发送成功";
       }else{
           return "请输入正确的邮箱地址";
       }
    }
    @ResponseBody
    @PostMapping("/find_password")
    public String find_password(@RequestParam String email,
                                @RequestParam String oldPassword,
                                @RequestParam String newPassword,
                                @RequestParam int VerificationCode) {
        Integer VCode = (Integer) redisTemplate.opsForValue().get(email);
        if (VCode == null) return "请重新发送验证码";
        if (!VCode.equals(VerificationCode)) return "验证码不正确";
        if (oldPassword.equals(newPassword)) {
            server.UpdatePassword(email, newPassword);
            return "密码修改成功";
        } else {
            return "前后密码不一致";
        }
    }
}
