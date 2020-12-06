package com.systest.javadev.songplaylist.controller;

import com.systest.javadev.songplaylist.model.PlaylistSongs;
import com.systest.javadev.songplaylist.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RootController {

    @Autowired
    PlaylistService playlistService;

    @GetMapping("/")
    public String  getHome(){
        return "home";
    }

    @MessageMapping("/allPlaylist")
    @SendTo("/getAllPlaylist")
    public List<PlaylistSongs> getAllPlaylist(){
        List<PlaylistSongs> playlistSongsList   = new ArrayList<>();
        playlistSongsList   = playlistService.getOnlyPlaylist();
        return playlistSongsList;
    }
}
