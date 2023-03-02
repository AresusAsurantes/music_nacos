package com.Areusus.music.api.controller;

import com.Aresus.music.service.SongService;
import com.Aresus.music.pojo.Song;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("song")
public class SongController {

    @DubboReference(version = "1.0.0")
    private SongService songService;

    @GetMapping
    public List<Song> findAllSong(){ return songService.findAll();}

    @GetMapping("detail")
    public List<Song> findSongById(@RequestParam("id") String id){
        return songService.findSongById(Integer.parseInt(id));
    }

    @GetMapping("singer/detail")
    public List<Song> findSongBySingerId(@RequestParam("singerId") String singerId){
        return songService.findSongBySingerId(Integer.parseInt(singerId));
    }

    @GetMapping("singerName/detail")
    public List<Song> findSongBySingerName(@RequestParam("name") String singerName){
        return songService.findSongBySingerName(singerName);
    }

    @GetMapping("name/detail")
    public List<Song> findSongByName(@RequestParam("name") String songName){
        return songService.findSongByName(songName);
    }
}
