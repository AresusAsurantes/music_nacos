package com.Areusus.music.api.controller;

import com.Aresus.music.model.vo.Result;
import com.Aresus.music.pojo.Collection;
import com.Aresus.music.service.CollectionService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("collection")
public class CollectionController {

    @DubboReference(version = "1.0.0")
    private CollectionService collectionService;

    @GetMapping()
    public List<Collection> findAllCollection(){
        return collectionService.findAll();
    }

    @GetMapping("detail")
    public List<Collection> getCollectionByUserId(@RequestParam("userId") String userId){
        return collectionService.getCollectionByUserId(Integer.parseInt(userId));
    }

    @PostMapping("add")
    public Result addCollection(HttpServletRequest request){
        return collectionService.addCollection(request);
    }

    @PostMapping("delete")
    public boolean deleteCollectedSong(@RequestParam("songId") String songId,
                                       @RequestParam("userId") String userId){
        return collectionService.deleteSongById(Integer.parseInt(songId), Integer.parseInt(userId));
    }

}
