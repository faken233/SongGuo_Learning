package service;

import pojo.Chapter;
import pojo.Course;
import pojo.Student;
import pojo.Teacher;
import pojo.question.Question;

import java.sql.Timestamp;
import java.util.List;

public interface TeacherService {

    // 获取信息
    Teacher getInfo(int id);

    // 更新信息
    void updateInfo(Teacher teacher);

    // 添加课程
    void addNewCourse(int teacherID, String courseName, String description, Timestamp startDateTime, Timestamp endDateTime, int maxStudents);

    // 查询教师名下的课程
    List<Course> selectCourses(int teacherID);

    // 查询课程的所有章节
    List<Chapter> selectChaptersByCourseID(int courseID);

    // 添加新章节
    void addNewChapter(Chapter chapter);

    // 添加一道新题目, 传入type
    void addQuestionToChapter(Question question, int type);

    // 查询章节的所有题目
    List<Question> selectQuestionsByChapterID(int chapterID);

    // 获取所有参与课程的学生
    List<Student> getEnrolledStudentsByCourseID(int courseID, int currentPage, int pageSize);
}
