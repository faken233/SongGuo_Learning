package service.impl;

import dao.AccountMapper;
import dao.StudentMapper;
import pojo.Course;
import pojo.EnrolledCourseMap;
import pojo.Student;
import service.StudentService;
import utils.mybatis.utils.MapperProxyFactory;

import java.util.ArrayList;
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
    public List<Course> selectParticipatableCourses(int studentID) {
        // 所有在时间内的课程
        List<Course> courses = studentMapper.selectParticipatableCourses();

        // 学生已经参与过的课程
        List<EnrolledCourseMap> enrolledCourseMaps = studentMapper.selectAlreadyParticipatedCourses(studentID);

        // courses里面剔除掉enrolledCourseMaps含有的课程
        // 1.拿到所有参与过的课程ID
        List<Integer> enrolledCourseIDs = new ArrayList<>();
        for (EnrolledCourseMap enrolledCourseMap : enrolledCourseMaps) {
            enrolledCourseIDs.add(enrolledCourseMap.getCourseID());
        }

        // 2.遍历courses, 剔除ID包括在enrolledCourseIDs里面的课程
        courses.removeIf(course -> enrolledCourseIDs.contains(course.getCourseID()));

        // 得到所有可参与的课程courses
        return courses;
    }

    @Override
    public int participateCourse(int studentID, int courseID) {
        return studentMapper.participateCourse(studentID, courseID);
    }

    @Override
    public List<Course> selectParticipatedCourses(int studentID) {
        // 所有在时间内的课程
        List<Course> courses = studentMapper.selectParticipatableCourses();

        // 学生已经参与过的课程
        List<EnrolledCourseMap> enrolledCourseMaps = studentMapper.selectAlreadyParticipatedCourses(studentID);

        // 学生参与过的课程ID的集合
        List<Integer> enrolledCourseIDs = new ArrayList<>();
        for (EnrolledCourseMap enrolledCourseMap : enrolledCourseMaps) {
            enrolledCourseIDs.add(enrolledCourseMap.getCourseID());
        }

        //courses里面根据Maps取得学生参与过的课程
        courses.removeIf(course -> !(enrolledCourseIDs.contains(course.getCourseID())));

        return courses;
    }
}































