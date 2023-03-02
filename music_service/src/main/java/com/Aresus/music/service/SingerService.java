package com.Aresus.music.service;

import com.Aresus.music.pojo.Singer;

import java.util.List;

public interface SingerService {
    List<Singer> findAll();

    List<Singer> findSingerBySex(String sex);

    List<Singer> findSingerByName(String name);

    List<Singer> findSingerByNameEs(String name);

    void storeSinger();
}
