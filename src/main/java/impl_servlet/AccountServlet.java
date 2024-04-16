package impl_servlet;

import base_servlet.AccountBaseServlet;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import pojo.Result;
import service.AccountService;
import service.impl.AccountServiceImpl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/account/*")
public class AccountServlet extends AccountBaseServlet {
    private final AccountService accountService = new AccountServiceImpl();

    /**
     *
     * @param req
     * @param resp
     * @throws IOException
     * @description:    注册业务的验证模块, 前端传入用户ID, 查询数据库是否存在此ID, 无则生成盐值返回前端
     *                  有则返回错误提示
     */
    public void validateIsNewAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String phoneNumber = req.getParameter("phoneNumber");
        int accountType = Integer.parseInt(req.getParameter("type"));

        Object received = null;
        if (accountType == 1) {
            received = accountService.validateIsNewTeacherAccount(phoneNumber);
        }
        else if (accountType == 2) {
            received = accountService.validateIsNewStudentAccount(phoneNumber);
        }

        // 验证是否为新用户
        if (received == null) {
            // b为真值则未找到传入的userID对应的盐值, 即新用户, 生成盐值返回前端进行加密后再次传输给后台
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            String rs = JSON.toJSONString(Result.success("VALIDATION_PASSED"));
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write(rs);
        }else {
            // 用户已存在
            String rs = JSON.toJSONString(Result.error("VALIDATION_FAILED"));
            resp.getWriter().write(rs);
        }
    }

    public void createAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 前端传输的一个账户信息会包含id/已加密密码/盐值/身份类型/教师凭证, 需要进行处理
        resp.setContentType("text/json;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        String reqData = req.getReader().readLine();
        if (reqData == null && req.getMethod().equals("OPTIONS")) {
            resp.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        JSONObject jsonObject = JSON.parseObject(reqData);
        int i = accountService.createNewAccount(jsonObject);
        if (i > 0) {
            String rs = JSON.toJSONString(Result.success("SIGN_UP_SUCCESS", i));
            resp.getWriter().write(rs);
            resp.setStatus(HttpServletResponse.SC_OK);
        } else if(i == 0){
            String rs = JSON.toJSONString(Result.error("SIGN_UP_FAILED"));
            resp.getWriter().write(rs);
        } else if (i == -1) {
            String rs = JSON.toJSONString(Result.error("TEACHER_VALIDATION_FAILED"));
            resp.getWriter().write(rs);
        }
    }

    public void loginValidation(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/json;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        String userid = req.getParameter("userid");
        String type = req.getParameter("type");
        Object o = accountService.loginValidation(userid, type);
        if (o != null) {
            // 存在用户, 返回盐值
            String rs = JSON.toJSONString(Result.success("LOGIN_VALIDATION_SUCCESS"));
            resp.getWriter().write(rs);
        }
        else {
            String rs = JSON.toJSONString(Result.error("LOGIN_VALIDATION_FAILED"));
            resp.getWriter().write(rs);
        }
    }

    public void loginConfirmation(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/json;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        String type = req.getParameter("type");
        String userid = req.getParameter("userid");
        String password = req.getParameter("password");
        int confirmation = accountService.loginConfirmation(userid, password, type);
        if (confirmation == 1) {
            String rs = JSON.toJSONString(Result.success("LOGIN_CONFIRMATION_SUCCESS"));
            resp.getWriter().write(rs);
        } else if (confirmation == 0) {
            String rs = JSON.toJSONString(Result.error("LOGIN_CONFIRMATION_FAILED"));
            resp.getWriter().write(rs);
        }
    }
}







































