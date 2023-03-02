package com.Aresus.music.service.impl;

import com.Aresus.music.dao.RankListMapper;
import com.Aresus.music.model.vo.Result;
import com.Aresus.music.pojo.RankList;
import com.Aresus.music.service.RankListService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@DubboService(version = "1.0.0", interfaceClass = RankListService.class)
public class RankListServiceImpl implements RankListService {

    @Autowired
    private RankListMapper rankListMapper;

    @Override
    public int getRankBySongListId(int songListId) {
        LambdaQueryWrapper<RankList> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(RankList::getSongListId, songListId);
        List<RankList> rankLists = rankListMapper.selectList(queryWrapper);
        int total_num = rankLists.size();
        if(total_num == 0){
            return total_num;
        }
        int total_score = (int) rankLists.stream().map(RankList::getScore).count();
        return total_num/total_score;
    }

    @Override
    public Result addRank(HttpServletRequest request) {
        String songListId = request.getParameter("songListId").trim();
        String userId = request.getParameter("consumerId").trim();
        String score = request.getParameter("score");

        LambdaQueryWrapper<RankList> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RankList::getSongListId, songListId).eq(RankList::getConsumerId, userId);
        if(rankListMapper.selectOne(queryWrapper) != null){
            return Result.fail();
        }

        RankList rankList = new RankList();
        rankList.setScore(Integer.parseInt(score));
        rankList.setSongListId(Long.parseLong(songListId));
        rankList.setConsumerId(Long.parseLong(userId));

        int insert = rankListMapper.insert(rankList);
        if(insert <= 0){
            return Result.fail(500,"评分失败");
        }

        return Result.success();
    }
}
