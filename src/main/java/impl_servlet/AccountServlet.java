package impl_servlet;

import base_servlet.AccountBaseServlet;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import constnum.ConstString;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import pojo.Result;
import pojo.Student;
import pojo.Teacher;
import service.AccountService;
import service.impl.AccountServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings("unused")
@WebServlet("/account/*")
public class AccountServlet extends AccountBaseServlet {
    private final AccountService accountService = new AccountServiceImpl();

    public void validateIsNewAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String phoneNumber = req.getParameter("phoneNumber");
        int accountType = Integer.parseInt(req.getParameter("type"));

        try {
            Object received = null;
            if (accountType == 1) {
                received = accountService.validateIsNewTeacherAccount(phoneNumber);
            }
            else if (accountType == 2) {
                received = accountService.validateIsNewStudentAccount(phoneNumber);
            }

            // 验证是否为新用户
            if (Objects.isNull(received)) {
                // 未找到传入的电话对应的用户
                resp.getWriter().write(JSON.toJSONString(Result.success("VALIDATION_PASSED")));
            }else {
                // 用户已存在
                resp.getWriter().write(JSON.toJSONString(Result.error("VALIDATION_FAILED")));
            }
        } catch (IOException e) {
            resp.getWriter().write(JSON.toJSONString(Result.error("INTERNAL_SERVER_ERROR")));
            throw new RuntimeException(e);
        }
    }

    public void createAccount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 前端传输的一个账户信息会包含id/已加密密码/身份类型/教师凭证, 需要进行处理
        String reqData = req.getReader().readLine();

        JSONObject jsonObject = JSON.parseObject(reqData);
        try {
            int i = accountService.createNewAccount(jsonObject);
            if (i > 0) {
                String rs = JSON.toJSONString(Result.success("SIGN_UP_SUCCESS", i));
                resp.getWriter().write(rs);
                resp.setStatus(HttpServletResponse.SC_OK);
            }
             else if (i == -1) {
                String rs = JSON.toJSONString(Result.error("TEACHER_VALIDATION_FAILED"));
                resp.getWriter().write(rs);
            }
        } catch (IOException e) {
            resp.getWriter().write(JSON.toJSONString(Result.error("SIGN_UP_FAILED")));
            throw new RuntimeException(e);
        }
    }

    public void loginValidation(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String certificate = req.getParameter("certificate");
        String type = req.getParameter("type");
        try {
            Object o = accountService.loginValidation(certificate, type);
            if (o != null) {
                // 存在用户
                resp.getWriter().write(JSON.toJSONString(Result.success("LOGIN_VALIDATION_SUCCESS")));
            }
            else {
                resp.getWriter().write(JSON.toJSONString(Result.error("LOGIN_VALIDATION_FAILED")));
            }
        } catch (IOException e) {
            resp.getWriter().write(JSON.toJSONString(Result.error("LOGIN_FAILED")));
            throw new RuntimeException(e);
        }
    }

    public void loginConfirmation(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String type = req.getParameter("type");
        String certificate = req.getParameter("certificate");
        String password = req.getParameter("password");
        try {
            Object o = accountService.loginConfirmation(certificate, password, type);
            if (o instanceof Teacher || o instanceof Student) {
                Map<String, Object> claims = new HashMap<>();
                claims.put("userData", o);
                String Jwt = Jwts.builder()
                        .signWith(SignatureAlgorithm.HS256, ConstString.JwtKey)// 签名算法 密钥
                        .setClaims(claims)// 载荷
                        .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000 * 3))// 三小时有效期
                        .compact();
                int id;
                if (o instanceof Teacher) {
                    id = ((Teacher) o).getteacherID();
                } else {
                    id = ((Student) o).getStudentID();
                }
                String rs = JSON.toJSONString(Result.success(String.valueOf(id), Jwt));
                resp.getWriter().write(rs);
            } else if (o == null) {
                String rs = JSON.toJSONString(Result.error("LOGIN_CONFIRMATION_FAILED"));
                resp.getWriter().write(rs);
            }
        } catch (IOException e) {
            resp.getWriter().write(JSON.toJSONString(Result.error("LOGIN_FAILED")));
            throw new RuntimeException(e);
        }
    }
}







































