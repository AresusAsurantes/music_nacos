package com.aresus.music.provider.test;

import com.Aresus.music.pojo.Singer;
import com.Aresus.music.service.CommentService;
import com.Aresus.music.service.SingerService;
import com.Aresus.music.service.SongService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class esTest {

    @Autowired
    private SingerService singerService;

    @Autowired
    private SongService songService;

    @Autowired
    private CommentService commentService;

    @Test
    public void putSinger(){
        singerService.storeSinger();
    }

    @Test
    public void printSinger(){
        List<Singer> singers = singerService.findSingerByNameEs("林");
        singers.forEach(System.out::println);
    }

    @Test
    public void putSong(){
        songService.putSong();
    }

    @Test
    public void getSongs(){
        songService.findSongByName("仰望星空").forEach(System.out::println);
    }

    @Test
    public void testDistributedLock() throws Exception{ commentService.testDistributedLock(); }
}
