package dao;

import pojo.Student;
import pojo.Teacher;
import utils.mybatis.anno.InsertFaken;
import utils.mybatis.anno.ParamFaken;
import utils.mybatis.anno.SelectFaken;

public interface AccountMapper {
    // 注册时根据手机号查找教师进行校验, 确保手机号对应唯一教师
    @SelectFaken("select * from teachers where phoneNumber = #{phoneNumber}")
    Teacher getTeacherByPhoneNumber(@ParamFaken("phoneNumber") String phoneNumber);

    // 注册时根据手机号查找教师进行校验, 确保手机号对应唯一教师
    @SelectFaken("select * from students where phoneNumber = #{phoneNumber}")
    Student getStudentByPhoneNumber(@ParamFaken("phoneNumber") String phoneNumber);

    // 登陆校验
    @SelectFaken("select * from teachers where teacherID = #{teacherID} and encryptedPassword = #{encryptedPassword}")
    Teacher getTeacherByIDAndPassword(@ParamFaken("teacherID") int teacherID, @ParamFaken("encryptedPassword") String encryptedPassword);

    // 登陆校验
    @SelectFaken("select * from students where studentID = #{studentID} and encryptedPassword = #{encryptedPassword}")
    Student getStudentByIDAndPassword(@ParamFaken("studentID") int studentID, @ParamFaken("encryptedPassword") String encryptedPassword);

    // 后续业务可根据ID查找教师信息
    @SelectFaken("select * from teachers where teacherID = #{teacherID}")
    Teacher getTeacherByID(@ParamFaken("teacherID") int teacherID);

    // 后续业务可根据ID查找学生信息
    @SelectFaken("select * from students where studentID = #{studentID}")
    Student getStudentByID(@ParamFaken("studentID") int studentID);

    // 注册校验通过后新增教师用户
    @InsertFaken("insert into teachers (teacherID, name, phoneNumber, encryptedPassword) values (#{teacherID}, #{name}, #{phoneNumber}, #{password})")
    void insertNewTeacher(@ParamFaken("teacherID") Integer teacherID, @ParamFaken("name") String name, @ParamFaken("phoneNumber") String phoneNumber, @ParamFaken("password") String password);

    // 注册校验通过后新增学生用户
    @InsertFaken("insert into students (studentID, name, phoneNumber, encryptedPassword) values (#{studentID}, #{name},  #{phoneNumber}, #{encryptedPassword})")
    void insertNewStudent(@ParamFaken("studentID") Integer studentID, @ParamFaken("name") String name, @ParamFaken("phoneNumber") String phoneNumber,@ParamFaken("encryptedPassword") String encryptedPassword);
}
