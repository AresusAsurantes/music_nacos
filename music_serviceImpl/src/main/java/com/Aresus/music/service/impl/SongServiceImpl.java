package com.Aresus.music.service.impl;

import com.Aresus.music.dao.SongMapper;
import com.Aresus.music.model.utils.ElasticModelUtils;
import com.Aresus.music.pojo.Singer;
import com.Aresus.music.pojo.Song;
import com.Aresus.music.pojo.es.SongEs;
import com.Aresus.music.service.SingerService;
import com.Aresus.music.service.SongService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.dubbo.config.annotation.DubboService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.Query;

import java.util.List;

@DubboService(version = "1.0.0", interfaceClass = SongService.class)
public class SongServiceImpl implements SongService {

    @Autowired
    private SongMapper songMapper;

    @Autowired
    private SingerService singerService;

    @Autowired
    private ElasticsearchRestTemplate restTemplate;


    @Override
    public List<Song> findAll() {
        return songMapper.selectList(Wrappers.lambdaQuery());
    }

    @Override
    public List<Song> findSongById(int id) {
        LambdaQueryWrapper<Song> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Song::getId, id);
        return songMapper.selectList(queryWrapper);
    }

    @Override
    public List<Song> findSongBySingerId(int singerId) {
        LambdaQueryWrapper<Song> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Song::getSingerId, singerId);
        return songMapper.selectList(queryWrapper);
    }

    @Override
    public List<Song> findSongBySingerName(String singerName) {
        List<Singer> singers = singerService.findSingerByNameEs(singerName);
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.matchQuery("name", singerName));
        Query query = new NativeSearchQuery(boolQueryBuilder);
        SearchHits<SongEs> search = restTemplate.search(query, SongEs.class);
        return ElasticModelUtils.copyProperties(search.getSearchHits(), Song.class);
    }

    @Override
    public List<Song> findSongByName(String songName) {
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("name", songName);
        Query query = new NativeSearchQuery(queryBuilder);
        SearchHits<SongEs> search = restTemplate.search(query, SongEs.class);
        return ElasticModelUtils.copyProperties(search.getSearchHits(), Song.class);
    }

    @Override
    public void putSong() {
        List<Song> songs = songMapper.selectList(Wrappers.lambdaQuery());
        for (Song song : songs) {
            SongEs songEs = new SongEs();
            BeanUtils.copyProperties(song, songEs);

            try {
                restTemplate.save(songEs);
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
