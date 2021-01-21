package com.systest.javadev.songplaylist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    @GetMapping("/whatsapp")
    public String testWhatsapp(){
        return "Hello Whatsapp";
    }

    @PostMapping("/whatsappMsg")
    public String getWhatsappMsg(@RequestParam String Body, @RequestParam String From,  @RequestParam String To,
                                 @RequestParam String MessageSid, @RequestParam String AccountSid, @RequestParam String MessagingServiceSid){

        System.out.println(Body+"====="+From+"====="+To+"\n\n"+MessageSid+"\n\n"+AccountSid+"\n\n"+MessagingServiceSid);
        return Body+"====="+From+"====="+To+"\n\n"+MessageSid+"\n\n"+AccountSid+"\n\n"+MessagingServiceSid;
    }
}
