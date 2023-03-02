package com.Aresus.music.service.impl;


import com.Aresus.music.dao.CommentMapper;
import com.Aresus.music.model.vo.Result;
import com.Aresus.music.model.vo.ThreadTask;
import com.Aresus.music.pojo.Comment;
import com.Aresus.music.pojo.User;
import com.Aresus.music.service.CommentService;
import com.Aresus.music.service.ThreadPoolService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@DubboService(version = "1.0.0", interfaceClass = CommentService.class)
public class CommentServiceImpl implements CommentService, Serializable {

    @Autowired
    private CommentMapper commentMapper;

    @DubboReference(version = "1.0.0")
    private ThreadPoolService threadPoolService;

    @Override
    public List<Comment> getAllComment() {
        return commentMapper.selectList(Wrappers.lambdaQuery());
    }

    @Override
    public List<Comment> getCommentBySongListId(int songListId) {
        LambdaQueryWrapper<Comment> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Comment::getSongListId, songListId);
        return commentMapper.selectList(queryWrapper);
    }

    @Override
    public List<Comment> getCommentBySongId(int songId) {
        LambdaQueryWrapper<Comment> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Comment::getSongId, songId);
        return commentMapper.selectList(queryWrapper);
    }

    @Override
    public Result<Object> addComment(User user, String type, String songListId, String songId, String content) {
        Comment comment = new Comment();

        comment.setUserId(user.getId());
        comment.setType(Byte.parseByte(type));
        if(songListId != null) {
            comment.setSongListId(Integer.parseInt(songListId));
        }
        else {
            comment.setSongId(Integer.parseInt(songId));
        }
        comment.setContent(content);
        comment.setCreateTime(new Date());

        ThreadTask task = new ThreadTask(() -> commentMapper.insert(comment));
        threadPoolService.doTask(task);

        return Result.success();
    }

    @Override
    public Result<Object> like(String id, String up) {
        LambdaUpdateWrapper<Comment> queryWrapper = Wrappers.lambdaUpdate();
        queryWrapper.eq(Comment::getId,id);
        Comment comment = new Comment();
        comment.setUp(Integer.parseInt(up));

        ThreadTask task = new ThreadTask(()->commentMapper.update(comment,queryWrapper));

        threadPoolService.doTask(task);

        return Result.success();
    }

    @Override
    public Result<Object> updateComment(HttpServletRequest request) {
        String id = request.getParameter("id").trim();
        String user_id = request.getParameter("userId").trim();
        String song_id = request.getParameter("songId").trim();
        String song_list_id = request.getParameter("songListId").trim();
        String content = request.getParameter("content").trim();
        String type = request.getParameter("type").trim();
        String up = request.getParameter("up").trim();

        Comment comment = new Comment(Integer.parseInt(id), Integer.parseInt(user_id), Integer.parseInt(song_id),
                Integer.parseInt(song_list_id), content, new Date(), Byte.valueOf(type), Integer.parseInt(up));

        ThreadTask task = new ThreadTask(()->commentMapper.updateById(comment));

        threadPoolService.doTask(task);

        return Result.success();
    }

    @Override
    public void testDistributedLock() throws Exception{

        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setTimeout(1000);
        RedissonClient redissonClient = Redisson.create(config);

        RLock myLock = redissonClient.getLock("myLock");

        ThreadTask task = new ThreadTask(()->{
            try{
                System.out.println(Thread.currentThread().getName()+":获取了锁");
                myLock.lock();
                System.out.println(Thread.currentThread().getName()+"操作了数据库");
            }
            catch (Exception e){
                e.printStackTrace();
            }
            finally {
                System.out.println(Thread.currentThread().getName()+":释放了锁");
                myLock.unlock();
            }
        });

        threadPoolService.doTask(task);
    }
}
