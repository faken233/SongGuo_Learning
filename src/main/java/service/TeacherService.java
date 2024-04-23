package service;

import pojo.Chapter;
import pojo.Course;
import pojo.Student;
import pojo.Teacher;
import pojo.question.Question;

import java.sql.Timestamp;
import java.util.List;

public interface TeacherService {
    Teacher getInfo(int id);
    int updateInfo(Teacher teacher);
    int addNewCourse(int teacherID, String courseName, String description, Timestamp startDateTime, Timestamp endDateTime, int maxStudents);
    List<Course> selectCourses(int teacherID);
    List<Chapter> selectChaptersByCourseID(int courseID);

    int addNewChapter(Chapter chapter);

    int addQuestionToChapter(Question question, int type);

    List<Question> selectQuestionsByChapterID(int chapterID);

    List<Student> getEnrolledStudentsByCourseID(int courseID, int currentPage, int pageSize);
}
