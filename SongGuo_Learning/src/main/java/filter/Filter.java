package filter;

import com.alibaba.fastjson.JSON;
import pojo.Result;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter("/*")
public class Filter implements javax.servlet.Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setContentType("application/json");
        request.setCharacterEncoding("UTF-8");
        String requestURI = request.getRequestURI();


        if (request.getMethod().equals("OPTIONS")) {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            response.setHeader("Access-Control-Allow-Headers", "*");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        if (requestURI.contains("/account/")) {
            // 登陆注册请求直接放行
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        // 判断是否携带Jwt令牌
        String token = request.getHeader("token");
        if (token == null) {
            // 未登录
            response.getWriter().write(JSON.toJSONString(Result.error("TOKEN_IS_NULL")));
        } else {
            // 校验Jwt, 逻辑忽略, 默认有Jwt就是登陆了
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }


    @Override
    public void destroy() {

    }
}
