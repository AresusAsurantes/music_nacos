package com.Areusus.music.api.service;

import com.Aresus.music.model.vo.Result;
import com.Aresus.music.pojo.User;
import com.Aresus.music.service.UserMemberService;
import com.Aresus.music.sso.service.LoginService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @DubboReference(version = "1.0.0")
    private LoginService loginService;

    @DubboReference(version = "1.0.0")
    private UserMemberService userMemberService;

    public Result<Object> login(String username, String password){
        return loginService.login(username, password);
    }

    public List<User> getUserById(String userId) {
        return userMemberService.getUserById(userId );
    }
}
