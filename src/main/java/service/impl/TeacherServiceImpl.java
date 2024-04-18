package service.impl;

import constnum.ConstNum;
import dao.AccountMapper;
import dao.TeacherMapper;
import pojo.Course;
import pojo.Teacher;
import service.TeacherService;
import utils.mybatis.utils.MapperProxyFactory;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

public class TeacherServiceImpl implements TeacherService {
    private final AccountMapper accountMapper = MapperProxyFactory.getMapper(AccountMapper.class);
    private final TeacherMapper teacherMapper = MapperProxyFactory.getMapper(TeacherMapper.class);
    @Override
    public Teacher getInfo(int id) {
        return accountMapper.getTeacherByID(id);
    }

    @Override
    public int updateInfo(Teacher teacher) {
        String name = teacher.getname();
        String email = teacher.getemail();
        String qq = teacher.getQq();
        String description = teacher.getDescription();
        Integer id = teacher.getteacherID();

        return teacherMapper.updateTeacher(name, email, qq, description, id);
    }

    @Override
    public int addNewCourse(int teacherID, String courseName, String description, Timestamp startDateTime, Timestamp endDateTime, int maxStudents) {
        int courseID = generateCourseID();
        return teacherMapper.addNewCourse(courseID, teacherID, courseName, description, startDateTime, endDateTime, maxStudents);
    }

    @Override
    public List<Course> selectCourses(int teacherID) {
        return teacherMapper.selectCourses(teacherID);
    }

    public int generateCourseID(){
        String firstDigit = String.valueOf(ConstNum.Course);

        // 根据当前时间生成接下来六位
        LocalDateTime now = LocalDateTime.now();
        String timePart = now.format(DateTimeFormatter.ofPattern("yyMMdd"));

        // 随机生成二位数
        String randomPart = generateRandomDigits();

        // 拼接生成完整的ID
        return Integer.parseInt(firstDigit + timePart + randomPart);
    }

    private String generateRandomDigits() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(4);
        for (int i = 0; i < 2; i++) {
            stringBuilder.append(random.nextInt(10)); // 生成0到9之间的随机数字
        }
        return stringBuilder.toString();
    }
}
