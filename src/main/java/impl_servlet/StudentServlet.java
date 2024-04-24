package impl_servlet;

import base_servlet.StudentBaseServlet;
import com.alibaba.fastjson.JSON;
import pojo.*;
import service.StudentService;
import service.impl.StudentServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("unused")
@WebServlet("/student/*")
public class StudentServlet extends StudentBaseServlet {
    private final StudentService studentService = new StudentServiceImpl();

    public void getInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        Student student;
        try {
            student = studentService.getInfo(id);
            resp.getWriter().write(JSON.toJSONString(Result.success("STUDENT_INFO_GET_OK", student)));
        } catch (Exception e) {
            resp.getWriter().write(JSON.toJSONString(Result.error("STUDENT_INFO_GET_ERROR")));
            throw new RuntimeException(e);
        }
    }

    public void updateInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String s = req.getReader().readLine();
        Student student = JSON.parseObject(s, Student.class);
        try {
            studentService.updateInfo(student);
            resp.getWriter().write( JSON.toJSONString(Result.success("UPDATE_STUDENT_INFO_OK")));
        } catch (Exception e) {
            resp.getWriter().write(JSON.toJSONString(Result.error("UPDATE_STUDENT_INFO_ERROR")));
            throw new RuntimeException(e);
        }
    }

    public void selectParticipatableCourses(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ClassNotFoundException, InterruptedException {
        int studentID = Integer.parseInt(req.getParameter("studentID"));
        List<Course> courses = studentService.selectParticipatableCourses(studentID);
        String rs = JSON.toJSONString(Result.success("SELECT_OK", courses));
        resp.getWriter().write(rs);
    }

    public void participateCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int studentID = Integer.parseInt(req.getParameter("studentID"));
        int courseID = Integer.parseInt(req.getParameter("courseID"));

        try {
            int i = studentService.participateCourse(studentID, courseID);
            if (i == 1) {
                resp.getWriter().write(JSON.toJSONString(Result.success("ENROLL_OK")));
            } else if (i == 0) {
                resp.getWriter().write(JSON.toJSONString(Result.error("MAX_STUDENT")));
            }
        } catch (IOException e) {
            resp.getWriter().write(JSON.toJSONString(Result.error("ENROLL_ERROR")));
            throw new RuntimeException(e);
        }
    }

    public void selectParticipatedCourses(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int studentID = Integer.parseInt(req.getParameter("studentID"));
        try {
            List<Course> courses = studentService.selectParticipatedCourses(studentID);
            resp.getWriter().write(JSON.toJSONString(Result.success("SELECT_OK", courses)));
        } catch (Exception e) {
            resp.getWriter().write(JSON.toJSONString(Result.error("SELECT_ERROR")));
            throw new RuntimeException(e);
        }
    }

    public void getChapterLearningProgress(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int studentID = Integer.parseInt(req.getParameter("studentID"));
        int chapterID = Integer.parseInt(req.getParameter("chapterID"));

        try {
            ChapterLearningProgress chapterLearningProgresses = studentService.selectChapterLearningProgressByStudentIDAndChapterID(studentID, chapterID);
            String rs;
            if (Objects.isNull(chapterLearningProgresses)) {
                rs = JSON.toJSONString(Result.success("FIRST_TIME", null));
            }else {
                rs = JSON.toJSONString(Result.success("ALREADY_DONE", chapterLearningProgresses));
            }
            resp.getWriter().write(rs);
        } catch (Exception e) {
            resp.getWriter().write(JSON.toJSONString(Result.error("SELECT_ERROR")));
            throw new RuntimeException(e);
        }

    }

    public void checkChapterAnswers(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int chapterID = Integer.parseInt(req.getParameter("chapterID"));
        String s = req.getReader().readLine();
        List<StudentAnswer> studentAnswers = JSON.parseArray(s, StudentAnswer.class);
        try {
            ChapterLearningProgress chapterLearningProgress = studentService.checkChapterAnswers(studentAnswers, chapterID);
            resp.getWriter().write(JSON.toJSONString(Result.success("OK", chapterLearningProgress)));
        } catch (Exception e) {
            resp.getWriter().write(JSON.toJSONString(Result.error("SELECT_ERROR")));
            throw new RuntimeException(e);
        }
    }
}
