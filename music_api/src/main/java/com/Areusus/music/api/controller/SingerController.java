package com.Areusus.music.api.controller;

import com.Aresus.music.service.SingerService;
import com.Aresus.music.pojo.Singer;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("singer")
public class SingerController {

    @DubboReference(version = "1.0.0")
    private SingerService singerService;

    @GetMapping
    public List<Singer> singer(){ return singerService.findAll();}

    /**
     *
     * 歌手修改功能，暂时不搞
     *
     */
//    @PostMapping("avatar/update")
//    public Result updateAvatar(@RequestParam("file") MultipartFile multipartFile,
//                               @RequestParam("id") String singerId){
//        return singerService.updateAvatar(multipartFile, singerId);
//    }
//
//    @PostMapping("update")
//    public JSONObject updateSinger(HttpServletRequest request){
//        return singerService.updateSinger(request);
//    }
//
//    @PostMapping("add")
//    public JSONObject addSinger(HttpServletRequest request){
//        return singerService.addSinger(request);
//    }
//
//    @PostMapping("delete")
//    public JSONObject deleteSinger(@RequestParam("id") String singerId){
//        return singerService.deleteSinger(Integer.parseInt(singerId));
//    }

    @GetMapping("sex/detail")
    public List<Singer> findSingerBySex(@RequestParam("sex") String sex){
        return singerService.findSingerBySex(sex);
    }

    @GetMapping("name/detail")
    public List<Singer> findSingerByName(@RequestParam("name") String name){
        return singerService.findSingerByName(name);
    }
}