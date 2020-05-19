package com.zm.inference.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Description 自定义的拦截器
 * @Author zm
 * @Date 2020/5/15 10:41
 **/
@Slf4j
@Component
public class SessionInterceptor implements HandlerInterceptor {

    /*@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("----------登录状态拦截----------");

        HttpSession session = request.getSession();
        log.info("sessionID: "+session.getId());

        // 获取用户信息，如果没有用户信息就直接返回提示信息
        Object userNow = session.getAttribute("plusUser");
        if (userNow == null){
            log.info("未登录");
            response.getWriter().write("请先登录");
            return false;
        }
        return true;
    }*/

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
