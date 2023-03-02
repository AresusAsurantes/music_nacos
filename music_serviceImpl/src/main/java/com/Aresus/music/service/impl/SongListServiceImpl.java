package com.Aresus.music.service.impl;

import com.Aresus.music.dao.SongListMapper;
import com.Aresus.music.pojo.SongList;
import com.Aresus.music.pojo.es.SongListEs;
import com.Aresus.music.service.SongListService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import java.util.List;

@DubboService(version = "1.0.0", interfaceClass = SongListService.class)
public class SongListServiceImpl implements SongListService {

    @Autowired
    private SongListMapper songListMapper;

    @Autowired
    private ElasticsearchRestTemplate restTemplate;

    @Override
    public List<SongList> findAll() {
        return songListMapper.selectList(Wrappers.lambdaQuery());
    }

    @Override
    public List<SongList> findSongListByStyle(String style) {
        LambdaQueryWrapper<SongList> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SongList::getStyle, style);
        return songListMapper.selectList(queryWrapper);
    }

    @Override
    public List<SongList> findSongListByTitle(String title) {
        LambdaQueryWrapper<SongList> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SongList::getTitle, title);
        return songListMapper.selectList(queryWrapper);
    }

    @Override
    public List<SongList> findSongListLikeTitle(String title) {
//        LambdaQueryWrapper<SongList> queryWrapper = Wrappers.lambdaQuery();
//        //TODO
//        //可以考虑用es来做歌名搜索
//        queryWrapper.like(SongList::getTitle, title);
//
//        return songListMapper.selectList(queryWrapper);

        return null;
    }

    @Override
    public void putSongList() {
        List<SongList> songLists = songListMapper.selectList(Wrappers.lambdaQuery());
        for (SongList songList : songLists) {
            SongListEs songListEs = new SongListEs();
            BeanUtils.copyProperties(songList, songListEs);

            try{
                restTemplate.save(songListEs);
            }
            catch (Exception e){
                if(!(e.getMessage()).contains("200") &&
                !(e.getMessage()).contains("201") &&
                !(e.getMessage()).contains("Created")){
                    throw e;
                }
            }
        }
    }


}
