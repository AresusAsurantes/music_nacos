package com.Areusus.music.api.handler;

import com.Aresus.music.model.vo.Result;
import com.Aresus.music.pojo.User;
import com.Aresus.music.sso.service.LoginService;
import com.Areusus.music.api.utils.UserThreadLocal;
import com.alibaba.nacos.api.utils.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class LoginHandler implements HandlerInterceptor {

    @DubboReference(version = "1.0.0")
    private LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        String userId = request.getParameter("userId");
        if(StringUtils.isBlank(userId)){
            PrintWriter writer = response.getWriter();
            writer.print("请先登陆！");
            return false;
        }

        //TODO
        //sso服务验证登陆
//        Result<Object> result = loginService.check(userId);
//        if(!result.isSuccess()){
//            return false;
//        }

        Result<Object> result = loginService.getUserInfo(userId);

        UserThreadLocal.setUser((User)result.getResult());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        UserThreadLocal.removeUser();
    }
}
