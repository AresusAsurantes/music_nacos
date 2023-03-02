package com.Aresus.music.service.impl;

import com.Aresus.music.dao.CollectionMapper;
import com.Aresus.music.model.vo.Result;
import com.Aresus.music.pojo.Collection;
import com.Aresus.music.service.CollectionService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@DubboService(version = "1.0.0", interfaceClass = CollectionService.class)
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionMapper collectionMapper;

    @Override
    public List<Collection> findAll() {
        return collectionMapper.selectList(new LambdaQueryWrapper<>());
    }

    @Override
    public List<Collection> getCollectionByUserId(int parseInt) {
        return collectionMapper.selectList(new LambdaQueryWrapper<Collection>().eq(Collection::getUserId, parseInt));
    }

    @Override
    public Result addCollection(HttpServletRequest request) {
        String user_id = request.getParameter("userId");
        String type = request.getParameter("type");
        String song_id = request.getParameter("songId");
        String song_list_id = request.getParameter("songListId");

        Collection collection = new Collection();

        collection.setUserId(Integer.parseInt(user_id));
        collection.setSongId(Integer.parseInt(song_id));
        collection.setSongListId(Integer.parseInt(song_list_id));
        if(StringUtils.isEquals(type, "0")){
            collection.setType(Byte.parseByte(type));
        }

        Collection collection1 = collectionMapper.selectById(song_list_id);
        if(collection1 != null){
            return Result.fail(500, "已收藏过歌单");
        }

        int insert = collectionMapper.insert(collection);
        if(insert <= 0){
            return Result.fail(500, "藏过失败");
        }

        return Result.success();
    }

    @Override
    public boolean deleteSongById(int songId, int userId) {
        LambdaQueryWrapper<Collection> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Collection::getSongId, songId).eq(Collection::getUserId, userId);
        return collectionMapper.delete(queryWrapper) > 0;
    }
}
