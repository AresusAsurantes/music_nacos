package com.Areusus.music.api.controller;

import com.Aresus.music.model.vo.Result;
import com.Aresus.music.pojo.ListSong;
import com.Aresus.music.service.ListSongService;
import com.alibaba.fastjson.JSONObject;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("listSong")
public class ListSongController {

    @DubboReference(version = "1.0.0")
    private ListSongService listSongService;

    @GetMapping("detail")
    public List<ListSong> findBySongListId(@RequestParam("songListId") String songListId){
        return listSongService.findBySongListId(songListId);
    }

    @PostMapping("add")
    public Result addSongToList(@RequestParam("songId") String songId,
                                @RequestParam("songListId") String songListId){
        return listSongService.addSongToList(Integer.parseInt(songId), Integer.parseInt(songListId));
    }
}
