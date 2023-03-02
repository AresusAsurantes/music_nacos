package com.Aresus.music.service;

import com.Aresus.music.pojo.Song;

import java.util.List;

public interface SongService {
    List<Song> findAll();

    List<Song> findSongById(int id);

    List<Song> findSongBySingerId(int singerId);

    List<Song> findSongBySingerName(String singerName);

    List<Song> findSongByName(String songName);

    void putSong();
}
