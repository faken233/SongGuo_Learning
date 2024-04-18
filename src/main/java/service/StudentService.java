package service;

import pojo.Course;
import pojo.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentService {
    Student getInfo(int studentID);

    int updateInfo(Student student);

    List<Course> selectParticipatableCourses() throws SQLException, ClassNotFoundException, InterruptedException;

    int participateCourse(int studentID, int courseID);


}
