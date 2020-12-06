package com.systest.javadev.songplaylist.service;

import com.systest.javadev.songplaylist.model.Songs;
import com.systest.javadev.songplaylist.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    @Autowired
    SongRepository songRepository;

    public List<Songs> getAllByPlaylist(int playlistId){
        return songRepository.findAllByPlaylist(playlistId);
    }

    public Songs addSong(Songs songs){
        return songRepository.save(songs);
    }

    public void deleteSong(int songId){
        songRepository.deleteById(songId);
    }
}
