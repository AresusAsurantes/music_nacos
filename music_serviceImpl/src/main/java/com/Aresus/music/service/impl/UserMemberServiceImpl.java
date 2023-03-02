package com.Aresus.music.service.impl;

import com.Aresus.music.dao.UserMapper;
import com.Aresus.music.pojo.User;
import com.Aresus.music.service.UserMemberService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService(version = "1.0.0", interfaceClass = UserMemberService.class)
public class UserMemberServiceImpl implements UserMemberService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUserById(String userId) {
        User user = userMapper.selectById(Integer.parseInt(userId));
        return List.of(user);
    }
}
