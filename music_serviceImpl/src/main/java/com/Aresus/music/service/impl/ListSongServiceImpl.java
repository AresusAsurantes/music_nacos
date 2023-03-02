package com.Aresus.music.service.impl;

import com.Aresus.music.dao.ListSongMapper;
import com.Aresus.music.model.vo.Result;
import com.Aresus.music.pojo.ListSong;
import com.Aresus.music.service.ListSongService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService(version = "1.0.0", interfaceClass = ListSongService.class)
public class ListSongServiceImpl implements ListSongService {

    @Autowired
    private ListSongMapper listSongMapper;

    @Override
    public List<ListSong> findBySongListId(String songListId) {
        LambdaQueryWrapper<ListSong> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(ListSong::getSongListId, songListId);
        return listSongMapper.selectList(queryWrapper);
    }

    @Override
    public Result addSongToList(int songId, int songListId) {
        ListSong listSong = new ListSong();
        listSong.setSongListId(songListId);
        listSong.setSongId(songId);
        int insert = listSongMapper.insert(listSong);
        if(insert <= 0){
            return Result.fail(500, "插入失败");
        }
        return Result.success();
    }
}
