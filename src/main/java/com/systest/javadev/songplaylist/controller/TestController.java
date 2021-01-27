package com.systest.javadev.songplaylist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    private static Map<String, String> msgTrackStack = new HashMap<>();

    @GetMapping("/whatsapp")
    public String testWhatsapp(){
        return "Hello Whatsapp";
    }

    @PostMapping("/whatsappMsg")
    public String getWhatsappMsg(@RequestParam String Body, @RequestParam String From,  @RequestParam String To){
        String msgTrack = msgTrackStack.get(From);
        msgTrack    = msgTrack != "" ? msgTrack+"~"+Body : Body;
        msgTrackStack.put(From, Body);
        //System.out.println(Body+"====="+From+"====="+To+"\n\n"+MessageSid+"\n\n"+AccountSid+"\n\n"+MessagingServiceSid);
        String mainMenu = "";
        String appintmentMenu   = "";
        String reviewMenu   = "";
        mainMenu    = "What can we do for you?\n" +
                "1: Appointment\n" +
                "2: Prescription\n" +
                "3: Reports\n" +
                "4: Review\n" +
                "5: Exit\n" +
                "Reply with your option.";
        appintmentMenu  = "11: New Appoinment\n" +
                "12: Cancel Appointment\n";
        reviewMenu  = "Please select your review option\n" +
                "41: Very Good\n" +
                "42: Good\n" +
                "43: Normal\n" +
                "44: Bad\n" +
                "45: Very Bad\n";
        /*if(Body.equals("Hi")) {
            return mainMenu;
        } else{
            return "Send a 'Hi' message to get customer service menu";
            //return Body + "==\n==" + From + "==\n==" + To;
        }*/
        System.out.println(msgTrackStack.get("Stack : "+From));
        switch (Body){
            case "Hi":
                return mainMenu;
            case "1":
                return appintmentMenu;
            case "2":
                return "";
            case "3":
                return "";
            case "4":
                return reviewMenu;
            default:
                return "Send a 'Hi' message to get customer service menu";
        }
    }
}
