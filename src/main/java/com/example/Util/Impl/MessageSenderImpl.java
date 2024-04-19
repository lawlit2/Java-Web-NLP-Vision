package com.example.Util.Impl;

import com.example.Util.MessageSender;
import jakarta.annotation.Resource;
import jakarta.mail.internet.MimeMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class MessageSenderImpl implements MessageSender {
    @Resource
    JavaMailSender sender;
    @Resource
    RedisTemplate<Object,Object> template;
    @Override
    public boolean SendMessage(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("当前你的验证码为：");
        Random random = new Random();
        int text = random.nextInt(900000)+100000;
        template.opsForValue().set(email,text,60*5, TimeUnit.SECONDS);
        message.setText("你当前的验证码为"+text+"有效时间为五分钟");
        message.setFrom("huxuzhendashuai@foxmail.com");
        message.setTo(email);
        sender.send(message);
        return false;
    }
}
