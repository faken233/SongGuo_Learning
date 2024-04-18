package impl_servlet;

import base_servlet.TeacherBaseServlet;
import com.alibaba.fastjson.JSON;
import pojo.Result;
import pojo.Teacher;
import service.TeacherService;
import service.impl.TeacherServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/teacher/*")
public class TeacherServlet extends TeacherBaseServlet {

    private final TeacherService teacherService = new TeacherServiceImpl();

    public void getInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        Teacher teacher = teacherService.getInfo(id);

        String rs = JSON.toJSONString(Result.success("OK", teacher));
        resp.getWriter().write(rs);
    }

    public void updateInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String s = req.getReader().readLine();
        Teacher teacher = JSON.parseObject(s, Teacher.class);
        int i = teacherService.updateInfo(teacher);
        String rs;
        if (i > 0) {
            rs = JSON.toJSONString(Result.success("UPDATE_OK"));
        } else {
            rs = JSON.toJSONString(Result.success("UPDATE_ERROR"));
        }
        resp.getWriter().write(rs);
    }
}
