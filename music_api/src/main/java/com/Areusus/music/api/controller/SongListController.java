package com.Areusus.music.api.controller;

import com.Aresus.music.pojo.SongList;
import com.Aresus.music.service.SongListService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("songList")
public class SongListController {

    @DubboReference(version = "1.0.0")
    private SongListService songListService;

    @GetMapping
    public List<SongList> songList(){ return songListService.findAll();}

    @GetMapping("style/detail")
    public List<SongList> returnSongListByStyle(@RequestParam("style") String style){
        return songListService.findSongListByStyle(style);
    }

    @GetMapping("title/detail")
    public List<SongList> returnSongListByTitle(@RequestParam("title") String title){
        return songListService.findSongListByTitle(title);
    }

    @GetMapping("likeTitle/detail")
    public List<SongList> returnSongListLikeTitle(@RequestParam("title") String title){
        return songListService.findSongListLikeTitle(title);
    }


}