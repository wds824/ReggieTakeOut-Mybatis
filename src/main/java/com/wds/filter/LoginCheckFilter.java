package com.wds.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wds.common.BaseContext;
import com.wds.common.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-09-21 9:25
 */
@WebFilter(urlPatterns = "*", filterName = "LoginCheckFilter")
@Slf4j
public class LoginCheckFilter implements Filter {
    private static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    //拦截未登录
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //放行静态资源
        String uri = request.getRequestURI();
        String[] uris = {
                "/front/**",
                "/backend/**",
                "/static/**",
                "/",
                "/employee/login",
                "/user/login",
        };

        if (!pathMatcher(uri, uris)) {
            // 不满足，校验Id
            Object empId = request.getSession().getAttribute("empId");
            Object userId = request.getSession().getAttribute("userId");
            if (empId != null || userId != null) {
                //已登录 放行
                BaseContext.setEmpId((Long) empId);
                BaseContext.setUserId((Long) userId);

            } else {
                //返回未登录
                ObjectMapper mapper = new ObjectMapper();
                response.getWriter().write(mapper.writeValueAsString(JsonResult.error("NOTLOGIN")));
                log.info("NOTLOGIN: {}", uri);
                return;
            }
        }

        //满足uri条件/已登录  放行
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * @param uri  用户访问uri
     * @param uris 直接放行的uri
     * @return 是否放行
     */
    public boolean pathMatcher(String uri, String[] uris) {
        for (String s : uris) {
            boolean match = PATH_MATCHER.match(s, uri);
            if (match)
                return true;
        }
        return false;
    }
}
