package impl_servlet;

import base_servlet.StudentBaseServlet;
import com.alibaba.fastjson.JSON;
import pojo.Result;
import pojo.Student;
import service.StudentService;
import service.impl.StudentServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/student/*")
public class StudentServlet extends StudentBaseServlet {
    private final StudentService studentService = new StudentServiceImpl();

    public void getInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        Student student = studentService.getInfo(id);

        String rs = JSON.toJSONString(Result.success("OK", student));
        resp.getWriter().write(rs);
    }

    public void updateInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String s = req.getReader().readLine();
        Student student = JSON.parseObject(s, Student.class);
        int i = studentService.updateInfo(student);
        String rs;
        if (i > 0) {
            rs = JSON.toJSONString(Result.success("UPDATE_OK"));
        } else {
            rs = JSON.toJSONString(Result.success("UPDATE_ERROR"));
        }
        resp.getWriter().write(rs);
    }
}
