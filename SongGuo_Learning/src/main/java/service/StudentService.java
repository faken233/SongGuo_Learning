package service;

import pojo.ChapterLearningProgress;
import pojo.Course;
import pojo.Student;
import pojo.StudentAnswer;

import java.sql.SQLException;
import java.util.List;

public interface StudentService {
    // 获取信息
    Student getInfo(int studentID);

    // 更新信息
    void updateInfo(Student student);

    // 查询可以参加的课程
    List<Course> selectParticipatableCourses(int studentID) throws SQLException, ClassNotFoundException, InterruptedException;

    // 参与课程
    int participateCourse(int studentID, int courseID);

    // 查询已经参加的课程
    List<Course> selectParticipatedCourses(int studentID) throws SQLException, ClassNotFoundException, InterruptedException;

    // 根据学生ID和章节ID查询章节学习情况
    ChapterLearningProgress selectChapterLearningProgressByStudentIDAndChapterID(Integer studentID, Integer chapterID) throws SQLException, ClassNotFoundException, InterruptedException;

    // 检查章节答题情况
    ChapterLearningProgress checkChapterAnswers(List<StudentAnswer> studentAnswers, Integer chapterID) throws SQLException, ClassNotFoundException, InterruptedException;
}
