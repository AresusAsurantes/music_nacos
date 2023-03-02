package com.Areusus.music.api.controller;

import com.Aresus.music.model.vo.Result;
import com.Aresus.music.pojo.Comment;
import com.Aresus.music.pojo.User;
import com.Aresus.music.service.CommentService;
import com.Areusus.music.api.utils.UserThreadLocal;
import com.alibaba.fastjson.JSONObject;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentController {

    @DubboReference(version = "1.0.0")
    private CommentService commentService;

    @GetMapping
    public List<Comment> allComment(){
        return commentService.getAllComment();
    }

    @GetMapping("songList/detail")
    public List<Comment> getCommentBySongListId(@RequestParam("songListId") String songListId){
        return commentService.getCommentBySongListId(Integer.parseInt(songListId));
    }

    @GetMapping("song/detail")
    public List<Comment> getCommentBySongId(@RequestParam("songId") String songId){
        return commentService.getCommentBySongId(Integer.parseInt(songId));
    }

    @PostMapping("add")
    public Result<Object> addComment(HttpServletRequest request){
        User user = UserThreadLocal.getUser();
//        io.netty.handler.codec.EncoderException
//        Dubbo 不能直接传HttpServletRequest请求，因为是远程调用，需要序列化
//        先从request里拿出参数再序列化！！！！！！！！
        String type = request.getParameter("type");
        String songListId = request.getParameter("songListId");
        String songId = request.getParameter("songId");
        String content = request.getParameter("content").trim();
        return commentService.addComment(user, type, songListId, songId, content);
    }

    @PostMapping("like")
    public Result<Object> like(@RequestParam("id") String id, @RequestParam("up") String up){

        return commentService.like(id, up);
    }

    @PostMapping("update")
    public Result<Object> updateComment(HttpServletRequest request){
        return commentService.updateComment(request);
    }

}