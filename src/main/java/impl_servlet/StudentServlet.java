package impl_servlet;

import base_servlet.StudentBaseServlet;
import com.alibaba.fastjson.JSON;
import pojo.Course;
import pojo.Result;
import pojo.Student;
import service.StudentService;
import service.impl.StudentServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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

    public void selectParticipatableCourses(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ClassNotFoundException, InterruptedException {
        int studentID = Integer.parseInt(req.getParameter("studentID"));
        List<Course> courses = studentService.selectParticipatableCourses(studentID);
        String rs = JSON.toJSONString(Result.success("OK", courses));
        resp.getWriter().write(rs);
    }

    public void participateCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int studentID = Integer.parseInt(req.getParameter("studentID"));
        int courseID = Integer.parseInt(req.getParameter("courseID"));
        int i = studentService.participateCourse(studentID, courseID);
        String rs;
        if (i > 0) {
            rs = JSON.toJSONString(Result.success("ENROLL_OK"));
        } else {
            rs = JSON.toJSONString(Result.success("ENROLL_ERROR"));
        }
        resp.getWriter().write(rs);
    }
}
