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
        ((HttpServletResponse)servletResponse).setHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse)servletResponse).setHeader("Access-Control-Allow-Headers", "Content-Type,XFILENAME,XFILECATEGORY,XFILESIZE");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
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
            System.out.println("requestURI: " + requestURI);
            System.out.println("before doFilter");
            filterChain.doFilter(servletRequest, servletResponse);
            System.out.println("after doFilter");
            return;
        }

        // 判断是否携带Jwt令牌
        String token = request.getHeader("token");
        if (token == null) {
            // 未登录, 重定向到登陆页
            System.out.println("token is null");
            String rs = JSON.toJSONString(Result.error("TOKEN_IS_NULL"));
            response.getWriter().write(rs);
        } else {
            System.out.println("login, token: " + token);
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
