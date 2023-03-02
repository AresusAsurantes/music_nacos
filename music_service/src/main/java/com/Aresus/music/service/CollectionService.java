package com.Aresus.music.service;

import com.Aresus.music.model.vo.Result;
import com.Aresus.music.pojo.Collection;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CollectionService {
    List<Collection> findAll();

    List<Collection> getCollectionByUserId(int parseInt);

    Result addCollection(HttpServletRequest request);

    boolean deleteSongById(int songId, int userId);
}
