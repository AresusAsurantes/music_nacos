package com.Areusus.music.api.controller;

import com.Aresus.music.model.vo.Result;
import com.Aresus.music.service.RankListService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("rankList")
public class RankListSongController {

    @DubboReference(version = "1.0.0")
    private RankListService rankListService;

    @GetMapping
    public int getRankBySongListId(@RequestParam("songListId") String songListId){
        return rankListService.getRankBySongListId(Integer.parseInt(songListId));
    }

    @PostMapping("add")
    public Result addRank(HttpServletRequest request){
        return rankListService.addRank(request);
    }

}
