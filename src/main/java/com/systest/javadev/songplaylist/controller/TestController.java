package com.systest.javadev.songplaylist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    private static Map<String, String> msgTrackStack = new HashMap<>();
    private static Map<String, HashMap<String, String>> feedbackBookingId = new HashMap<String, HashMap<String, String>>(){{
        put("+918089733774",new HashMap(){{put("BookingId","BKNO7894532");put("source","BIS");}});
        put("+919633118773",new HashMap(){{put("BookingId","BKNO7889455");put("source","BIS");}});
    }};

    @GetMapping("/whatsapp")
    public String testWhatsapp(){
        return "Hello Whatsapp";
    }

    @PostMapping("/feedback_response")
    public String feedbackResponse(@RequestParam String Body, @RequestParam String From,  @RequestParam String To){
        String response = "You have opted "+Body+" feedback for the booking "+feedbackBookingId.get(From.replace("whatsapp:",""));
        feedbackBookingId.remove(From.replace("whatsapp:",""));
        return response;
        //return Body+"====="+From+"======"+To;
    }

    @PostMapping("/statusCallBack")
    public void statusCallBack(HttpServletRequest request, HttpServletResponse httpServletResponse){
        System.out.println("Whatsapp Status Callback");
        System.out.println(request.getParameter("MessageSid"));
        System.out.println(request.getParameter("MessageStatus"));
    }

    @PostMapping("/whatsappMsg")
    public String getWhatsappMsg(@RequestParam String Body, @RequestParam String From,  @RequestParam String To){

        System.out.println("Map : "+msgTrackStack);
        String msgTrack = msgTrackStack.get(From);
        msgTrack    = msgTrack != null ? msgTrack+"~"+Body : Body;
        msgTrackStack.put(From, msgTrack);
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
        System.out.println("Stack : "+msgTrackStack.get(From));
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
