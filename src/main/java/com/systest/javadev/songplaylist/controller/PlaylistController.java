package com.systest.javadev.songplaylist.controller;

import com.systest.javadev.songplaylist.model.Playlist;
import com.systest.javadev.songplaylist.model.Songs;
import com.systest.javadev.songplaylist.service.PlaylistService;
import com.systest.javadev.songplaylist.service.SongService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Playlist")
public class PlaylistController {
    @Autowired
    PlaylistService playlistService;

    @Autowired
    SongService songService;

    @GetMapping("/all")
    public List<Playlist> getAllPlaylist(){
        return playlistService.getAllPlaylist();
    }

    @PostMapping("/addPlaylist")
    @ApiOperation(value="Add New Playlist", notes = "Method to create new playlist")
    public List<Playlist> addPlaylist(@RequestBody Playlist playlist){
        playlistService.addPlaylist(playlist);
        return playlistService.getAllPlaylist();
    }

    @PostMapping("/getPlaylistSong")
    public List<Songs> getPlaylistSong(@RequestParam String playlistId){
        return songService.getAllByPlaylist(Integer.parseInt(playlistId));
    }

    @PostMapping("/addPlaylistSong")
    @ApiOperation(value="Add Songs to Playlist", notes = "Method to add new songs to a playlist")
    public List<Playlist> addPlaylistSong(@RequestBody Songs songs){
        songService.addSong(songs);
        return playlistService.getAllPlaylist();
    }

    @PostMapping("/deletePlaylist")
    public List<Playlist> deletePlaylist(@RequestParam String playlistId){
        playlistService.deletePlaylist(Integer.parseInt(playlistId));
        return playlistService.getAllPlaylist();
    }

    @PostMapping("/deletePlaylistSong")
    public List<Playlist> deletePlaylistSong(@RequestParam String songId){
        songService.deleteSong(Integer.parseInt(songId));
        return playlistService.getAllPlaylist();
    }
}
