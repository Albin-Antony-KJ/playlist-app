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
    public String getWhatsappMsg(@RequestParam String Body, @RequestParam String From,  @RequestParam String To){

        //System.out.println(Body+"====="+From+"====="+To+"\n\n"+MessageSid+"\n\n"+AccountSid+"\n\n"+MessagingServiceSid);
        String mainMenu = "";
        mainMenu    = "What can we do for you?\n" +
                "1: Appointment\n" +
                "2: Prescription\n" +
                "3: Reports\n" +
                "4: Review\n" +
                "5: Exit\n" +
                "Reply with your option.";
        if(Body.equals("Hi")) {
            return mainMenu;
        } else{
            return "Send a 'Hi' message to get customer service menu";
            //return Body + "==\n==" + From + "==\n==" + To;
        }
    }
}
