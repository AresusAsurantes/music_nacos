package com.Aresus.music.service;

import com.Aresus.music.model.vo.Result;
import com.Aresus.music.pojo.Comment;
import com.Aresus.music.pojo.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CommentService {
    List<Comment> getAllComment();

    List<Comment> getCommentBySongListId(int songListId);

    List<Comment> getCommentBySongId(int songId);

    Result<Object> addComment(User user, String type, String songListId, String songId, String content);

    Result<Object> like(String id, String up);

    Result<Object> updateComment(HttpServletRequest request);

    void testDistributedLock() throws Exception;
}
