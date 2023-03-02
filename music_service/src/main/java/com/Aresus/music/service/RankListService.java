package com.Aresus.music.service;

import com.Aresus.music.model.vo.Result;

import javax.servlet.http.HttpServletRequest;

public interface RankListService {
     int getRankBySongListId(int songListId);

     Result addRank(HttpServletRequest request);
}
