package service.impl;

import dao.AccountMapper;
import dao.StudentMapper;
import pojo.Course;
import pojo.Student;
import service.StudentService;
import utils.mybatis.utils.MapperProxyFactory;

import java.sql.SQLException;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    private final AccountMapper accountMapper = MapperProxyFactory.getMapper(AccountMapper.class);
    private final StudentMapper studentMapper = MapperProxyFactory.getMapper(StudentMapper.class);
    @Override
    public Student getInfo(int id) {
        return accountMapper.getStudentByID(id);
    }

    @Override
    public int updateInfo(Student student) {

        String studentName = student.getName();
        String grade = student.getGrade();
        String className = student.getClassName();
        Integer studentID = student.getStudentID();

        return studentMapper.updateStudent(studentName, grade, className, studentID);
    }

    @Override
    public List<Course> selectParticipatableCourses() throws SQLException, ClassNotFoundException, InterruptedException {
        return studentMapper.selectParticipateCourses();
    }

    @Override
    public int participateCourse(int studentID, int courseID) {
        return studentMapper.participateCourse(studentID, courseID);
    }
}
