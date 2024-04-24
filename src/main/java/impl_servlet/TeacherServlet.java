package impl_servlet;

import base_servlet.TeacherBaseServlet;
import com.alibaba.fastjson.JSON;
import constnum.ConstNum;
import pojo.*;
import pojo.question.MultipleChoiceQuestion;
import pojo.question.Question;
import pojo.question.ShortAnswerQuestion;
import pojo.question.TrueFalseQuestion;
import service.TeacherService;
import service.impl.TeacherServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@SuppressWarnings("unused")
@WebServlet("/teacher/*")
public class TeacherServlet extends TeacherBaseServlet {

    private final TeacherService teacherService = new TeacherServiceImpl();

    public void getInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        try {
            Teacher teacher = teacherService.getInfo(id);
            resp.getWriter().write(JSON.toJSONString(Result.success("OK", teacher)));
        } catch (Exception e) {
            resp.getWriter().write(JSON.toJSONString(Result.error("ERROR")));
            throw new RuntimeException(e);
        }
    }

    public void updateInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String s = req.getReader().readLine();
        Teacher teacher = JSON.parseObject(s, Teacher.class);
        try {
            teacherService.updateInfo(teacher);
            resp.getWriter().write(JSON.toJSONString(Result.success("UPDATE_OK")));
        } catch (Exception e) {
            resp.getWriter().write(JSON.toJSONString(Result.error("UPDATE_ERROR")));
            throw new RuntimeException(e);
        }

    }

    public void addCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String s = req.getReader().readLine();
        Course course = JSON.parseObject(s, Course.class);

        // 解析, 取出属性值
        String courseName = course.getCourseName();
        String description = course.getDescription();
        int teacherID = course.getTeacherID();
        Timestamp startDateTime = course.getStartDateTime();
        Timestamp endDateTime = course.getEndDateTime();
        int maxStudents = course.getMaxStudents();

        try {
            teacherService.addNewCourse(teacherID, courseName, description, startDateTime, endDateTime, maxStudents);
            resp.getWriter().write(JSON.toJSONString(Result.success("ADD_OK")));
        } catch (Exception e) {
            resp.getWriter().write(JSON.toJSONString(Result.error("ADD_ERROR")));
            throw new RuntimeException(e);
        }
    }

    public void selectCourses(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int teacherID = Integer.parseInt(req.getParameter("id"));
        try {
            List<Course> courses = teacherService.selectCourses(teacherID);
            resp.getWriter().write(JSON.toJSONString(Result.success("SELECT_OK", courses)));
        } catch (IOException e) {
            resp.getWriter().write(JSON.toJSONString(Result.error("SELECT_ERROR")));
            throw new RuntimeException(e);
        }
    }

    public void selectCourseChaptersByCourseID(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int courseID = Integer.parseInt(req.getParameter("courseID"));
        try {
            List<Chapter> chapters = teacherService.selectChaptersByCourseID(courseID);
            resp.getWriter().write(JSON.toJSONString(Result.success("SELECT_OK", chapters)));
        } catch (IOException e) {
            resp.getWriter().write(JSON.toJSONString(Result.error("SELECT_ERROR")));
            throw new RuntimeException(e);
        }
    }

    public void addNewChapterToCourse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String s = req.getReader().readLine();
        Chapter chapter = JSON.parseObject(s, Chapter.class);
        try {
            teacherService.addNewChapter(chapter);
            resp.getWriter().write(JSON.toJSONString(Result.success("ADD_OK")));
        } catch (IOException e) {
            resp.getWriter().write(JSON.toJSONString(Result.error("ADD_ERROR")));
            throw new RuntimeException(e);
        }
    }

    public void addQuestionToChapter(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int type = Integer.parseInt(req.getParameter("questionType"));
        String s = req.getReader().readLine();
        Question question = null;
        if (type == ConstNum.TrueFalseQuestion) {
            question = JSON.parseObject(s, TrueFalseQuestion.class);
        } else if (type == ConstNum.ShortAnswerQuestion) {
            question = JSON.parseObject(s, ShortAnswerQuestion.class);
        } else if (type == ConstNum.MultipleChoiceQuestion) {
            question = JSON.parseObject(s, MultipleChoiceQuestion.class);
        }

        try {
            teacherService.addQuestionToChapter(question, type);
            resp.getWriter().write(JSON.toJSONString(Result.success("ADD_OK", question)));
        } catch (Exception e) {
            resp.getWriter().write(JSON.toJSONString(Result.error("ADD_ERROR")));
            throw new RuntimeException(e);
        }
    }

    public void getChapterQuestions (HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int chapterID = Integer.parseInt(req.getParameter("chapterID"));
        try {
            List<Question> questionList = teacherService.selectQuestionsByChapterID(chapterID);
            resp.getWriter().write(JSON.toJSONString(Result.success("SELECT_OK", questionList)));
        } catch (IOException e) {
            resp.getWriter().write(JSON.toJSONString(Result.error("SELECT_ERROR")));
            throw new RuntimeException(e);
        }
    }

    public void getEnrolledStudents (HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int courseID = Integer.parseInt(req.getParameter("courseID"));
        int currentPage = Integer.parseInt(req.getParameter("currentPage"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
        try {
            List<Student> enrolledStudentsByCourseID = teacherService.getEnrolledStudentsByCourseID(courseID, currentPage, pageSize);
            resp.getWriter().write(JSON.toJSONString(Result.success("SELECT_OK", enrolledStudentsByCourseID)));
        } catch (IOException e) {
            resp.getWriter().write(JSON.toJSONString(Result.error("SELECT_ERROR")));
            throw new RuntimeException(e);
        }
    }
}
