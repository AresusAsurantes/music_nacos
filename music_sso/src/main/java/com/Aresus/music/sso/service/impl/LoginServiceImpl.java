package com.Aresus.music.sso.service.impl;

import com.Aresus.music.dao.UserMapper;
import com.Aresus.music.model.vo.Result;
import com.Aresus.music.pojo.User;
import com.Aresus.music.sso.service.LoginService;
import com.Aresus.music.sso.util.JwtUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

@DubboService(version = "1.0.0", interfaceClass = LoginService.class)
public class LoginServiceImpl implements LoginService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result<Object> login(String username, String password) {
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(User::getPassword, password).eq(User::getUsername, username);
        User user = userMapper.selectOne(queryWrapper);
        if(user == null){
            return Result.fail(-999,"用户不存在");
        }
        String token = JwtUtils.createToken(user);
        user.setPassword(token);
        redisTemplate.opsForValue().set(token, "true");

        return Result.success(user);
    }

    @Override
    public Result<Object> check(String token) {

        Boolean hasKey = redisTemplate.hasKey(token);
        if(hasKey != null && !hasKey){
            return Result.fail(-999,"登陆过期");
        }

        User user = (User)JwtUtils.checkToken(token);
        if(user == null){
            return Result.fail(-999,"token不合法");
        }

        return Result.success(user);
    }

    @Override
    public Result<Object> getUserInfo(String userId) {
        User user = userMapper.selectById(Integer.parseInt(userId));
        if(user == null){
            return Result.fail(-999,"用户不存在");
        }
        return Result.success(user);
    }


}
