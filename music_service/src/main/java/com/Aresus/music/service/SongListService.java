package com.Aresus.music.service;

import com.Aresus.music.pojo.SongList;

import java.util.List;

public interface SongListService {
    List<SongList> findAll();

    List<SongList> findSongListByStyle(String style);

    List<SongList> findSongListByTitle(String title);

    List<SongList> findSongListLikeTitle(String title);

    void putSongList();
}
