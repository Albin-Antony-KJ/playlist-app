package com.systest.javadev.songplaylist.repository;

import com.systest.javadev.songplaylist.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {

    @Query(value="SELECT * FROM playlist;", nativeQuery = true)
    List<Playlist> getOnlyPlaylist();
}
