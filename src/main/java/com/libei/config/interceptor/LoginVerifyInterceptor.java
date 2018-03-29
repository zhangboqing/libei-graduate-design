package com.libei.config.interceptor;

import com.libei.constant.BusinessConstant;
import com.libei.domain.entity.LbLoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author zhangboqing
 * @date 2018/3/16
 */
public class LoginVerifyInterceptor implements HandlerInterceptor {

    Logger logger = LoggerFactory.getLogger(LoginVerifyInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        //获取当前用户信息
        LbLoginUser loginUser = (LbLoginUser)session.getAttribute(BusinessConstant.USER_LOGIN_KEY);
        if (Objects.nonNull(loginUser)) {
            return true;
        }

        logger.info("------:跳转到login页面！");
        response.sendRedirect(request.getContextPath()+"/"+"?flag=1");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
