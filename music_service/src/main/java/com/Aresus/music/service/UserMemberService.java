package com.Aresus.music.service;

import com.Aresus.music.pojo.User;

import java.util.List;

public interface UserMemberService {
    List<User> getUserById(String userId);
}
