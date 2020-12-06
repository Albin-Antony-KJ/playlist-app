package com.systest.javadev.songplaylist.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "playlist")
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playlist_id")
    private int playlistId;
    @Column(name = "playlist_name")
    private String playlistName;
    @OneToMany(mappedBy = "playlist", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Songs> songsList = new ArrayList<>();

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public List<Songs> getSongsList() {
        return songsList;
    }

    /*public void setSongsList(List<Songs> songsList) {
        this.songsList = songsList;
    }*/

    public void addSong(Songs song) {
        this.songsList.add(song);
    }

    public void removeSong(Songs song) {
        this.songsList.remove(song);
    }
}
