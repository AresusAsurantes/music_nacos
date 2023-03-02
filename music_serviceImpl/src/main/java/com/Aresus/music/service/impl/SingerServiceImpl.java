package com.Aresus.music.service.impl;

import com.Aresus.music.dao.SingerMapper;
import com.Aresus.music.pojo.Singer;
import com.Aresus.music.pojo.es.SingerEs;
import com.Aresus.music.service.SingerService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.dubbo.config.annotation.DubboService;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.Query;

import java.util.ArrayList;
import java.util.List;

@DubboService(version = "1.0.0", interfaceClass = SingerService.class)
public class SingerServiceImpl implements SingerService {

    @Autowired
    private SingerMapper singerMapper;

    @Autowired
    private ElasticsearchRestTemplate template;

    @Override
    public List<Singer> findAll() {
        return singerMapper.selectList(Wrappers.lambdaQuery());
    }

    @Override
    public List<Singer> findSingerBySex(String sex) {
        LambdaQueryWrapper<Singer> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Singer::getSex, Byte.parseByte(sex));
        return singerMapper.selectList(queryWrapper);
    }

    @Deprecated
    @Override
    public List<Singer> findSingerByName(String name) {
        LambdaQueryWrapper<Singer> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Singer::getName, name);
        return singerMapper.selectList(queryWrapper);
    }

    //使用es查找歌手信息
    @Override
    public List<Singer> findSingerByNameEs(String name) {
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("name", name);
        Query query = new NativeSearchQuery(queryBuilder);

        List<Singer> singers = new ArrayList<>();
        SearchHits<SingerEs> search = template.search(query, SingerEs.class);
        List<SearchHit<SingerEs>> searchHits = search.getSearchHits();
        for (SearchHit<SingerEs> searchHit : searchHits) {
            Singer singer = new Singer();
            BeanUtils.copyProperties(searchHit.getContent(), singer);
            singers.add(singer);
        }
        return singers;
    }

    @Override
    public void storeSinger() {
        List<Singer> singers = singerMapper.selectList(Wrappers.lambdaQuery());
        for (Singer singer : singers) {
            SingerEs singerEs = new SingerEs();
            BeanUtils.copyProperties(singer, singerEs);
            try {
                template.save(singerEs);
            }
//            异常处理，spingboot5无法解析es8及以上版本的json数据
            catch (Exception e){
                if(!(e.getMessage()).contains("200") &&
                        !(e.getMessage().contains("201") &&
                                !(e.getMessage()).contains("Created"))){
                    throw e;
                }
            }
        }
    }


}
