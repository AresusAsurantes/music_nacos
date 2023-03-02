package com.Aresus.music.sso.service;

import com.Aresus.music.model.vo.Result;

public interface LoginService {
    Result<Object> login(String username, String password);

    Result<Object> check(String token);

    Result<Object> getUserInfo(String userId);
}
