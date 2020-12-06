package com.systest.javadev.songplaylist.repository;

import com.systest.javadev.songplaylist.model.Songs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SongRepository extends JpaRepository<Songs, Integer> {

    public List<Songs> findAllByPlaylist(int playlistId);

    @Query(value = "SELECT * FROM songs WHERE playlist_id = :playlistId", nativeQuery = true)
    public List<Songs> getSongsByPlaylist(@Param("playlistId") int playlistId);
}
