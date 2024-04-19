package impl_servlet;

import base_servlet.TeacherBaseServlet;
import com.alibaba.fastjson.JSON;
import pojo.Chapter;
import pojo.Course;
import pojo.Result;
import pojo.Teacher;
import service.TeacherService;
import service.impl.TeacherServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

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

    public void addCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String s = req.getReader().readLine();
        Course course = JSON.parseObject(s, Course.class);

        String courseName = course.getCourseName();
        String description = course.getDescription();
        int teacherID = course.getTeacherID();
        Timestamp startDateTime = course.getStartDateTime();
        Timestamp endDateTime = course.getEndDateTime();
        int maxStudents = course.getMaxStudents();

        int i = teacherService.addNewCourse(teacherID, courseName, description, startDateTime, endDateTime, maxStudents);
        if (i > 0) {
            String rs = JSON.toJSONString(Result.success("ADD_OK"));
            resp.getWriter().write(rs);
        } else {
            String rs = JSON.toJSONString(Result.success("ADD_ERROR"));
            resp.getWriter().write(rs);
        }
    }

    public void selectCourses(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int teacherID = Integer.parseInt(req.getParameter("id"));
        List<Course> courses = teacherService.selectCourses(teacherID);
        String rs = JSON.toJSONString(Result.success("SELECT_OK", courses));
        resp.getWriter().write(rs);
    }

    public void selectCourseChaptersByCourseID(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int courseID = Integer.parseInt(req.getParameter("courseID"));
        List<Chapter> chapters = teacherService.selectChaptersByCourseID(courseID);
        String rs = JSON.toJSONString(Result.success("SELECT_OK", chapters));
        resp.getWriter().write(rs);
    }

    public void addNewChapterToCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String s = req.getReader().readLine();
        Chapter chapter = JSON.parseObject(s, Chapter.class);
        int i = teacherService.addNewChapter(chapter);
        String rs;
        if (i > 0) {
            rs = JSON.toJSONString(Result.success("ADD_OK"));
        } else {
            rs = JSON.toJSONString(Result.success("ADD_ERROR"));
        }
        resp.getWriter().write(rs);
    }
}
