package com.Aresus.music.service;

import com.Aresus.music.model.vo.Result;
import com.Aresus.music.pojo.ListSong;

import java.util.List;

public interface ListSongService {
    List<ListSong> findBySongListId(String songListId);

    Result addSongToList(int songId, int songListId);
}
