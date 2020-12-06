package com.systest.javadev.songplaylist.service;

import com.systest.javadev.songplaylist.model.Playlist;
import com.systest.javadev.songplaylist.model.PlaylistSongs;
import com.systest.javadev.songplaylist.repository.PlaylistRepository;
import com.systest.javadev.songplaylist.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlaylistService {
    @Autowired
    PlaylistRepository playlistRepository;

    @Autowired
    SongRepository songRepository;

    public List<Playlist> getAllPlaylist(){
        return playlistRepository.findAll();
    }

    public Playlist addPlaylist(Playlist playlist){
        return playlistRepository.save(playlist);
    }

    public void deletePlaylist(int playlistId){
        playlistRepository.deleteById(playlistId);
    }

    public List<PlaylistSongs> getOnlyPlaylist() {
        List<PlaylistSongs> playlistSongsList   = new ArrayList<>();
        for(Playlist temp : playlistRepository.findAll()){
            PlaylistSongs   object  = new PlaylistSongs();
            object.setPlaylistId(temp.getPlaylistId());
            object.setPlaylistName(temp.getPlaylistName());
            object.setSongsList(songRepository.getSongsByPlaylist(temp.getPlaylistId()));
            playlistSongsList.add(object);
        }
        return playlistSongsList;
    }

}
