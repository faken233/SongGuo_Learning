package service;

import pojo.ChapterLearningProgress;
import pojo.Course;
import pojo.Student;
import pojo.StudentAnswer;

import java.sql.SQLException;
import java.util.List;

public interface StudentService {
    Student getInfo(int studentID);

    int updateInfo(Student student);

    List<Course> selectParticipatableCourses(int studentID) throws SQLException, ClassNotFoundException, InterruptedException;

    int participateCourse(int studentID, int courseID);

    List<Course> selectParticipatedCourses(int studentID) throws SQLException, ClassNotFoundException, InterruptedException;

    ChapterLearningProgress selectChapterLearningProgressByStudentIDAndChapterID(Integer studentID, Integer chapterID) throws SQLException, ClassNotFoundException, InterruptedException;

    ChapterLearningProgress checkChapterAnswers(List<StudentAnswer> studentAnswers, Integer chapterID) throws SQLException, ClassNotFoundException, InterruptedException;



}
