package com.Areusus.music.api.controller;

import com.Aresus.music.model.vo.Result;
import com.Aresus.music.pojo.User;
import com.Areusus.music.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("login/status")
    public Result<Object> login(@RequestParam String username,
                      @RequestParam String password){
        return userService.login(username,password);
    }

    @GetMapping("detail")
    public List<User> getUserById(@RequestParam("id") String userId){
        return userService.getUserById(userId);
    }
}
