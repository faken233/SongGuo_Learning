package dao;

import pojo.Student;
import pojo.Teacher;
import utils.mybatis.anno.InsertFaken;
import utils.mybatis.anno.ParamFaken;
import utils.mybatis.anno.SelectFaken;

public interface AccountMapper {
    @SelectFaken("select * from teachers where phoneNumber = #{phoneNumber}")
    Teacher getTeacherByPhoneNumber(@ParamFaken("phoneNumber") String phoneNumber);

    @SelectFaken("select * from students where phoneNumber = #{phoneNumber}")
    Student getStudentByPhoneNumber(@ParamFaken("phoneNumber") String phoneNumber);

    @SelectFaken("select * from teachers where teacherID = #{teacherID} and encryptedPassword = #{encryptedPassword}")
    Teacher getTeacherByIDAndPassword(@ParamFaken("teacherID") int teacherID, @ParamFaken("encryptedPassword") String encryptedPassword);

    @SelectFaken("select * from students where studentID = #{studentID} and encryptedPassword = #{encryptedPassword}")
    Student getStudentByIDAndPassword(@ParamFaken("studentID") int studentID, @ParamFaken("encryptedPassword") String encryptedPassword);

    @SelectFaken("select * from teachers where teacherID = #{teacherID}")
    Teacher getTeacherByID(@ParamFaken("teacherID") int teacherID);

    @SelectFaken("select * from students where studentID = #{studentID}")
    Student getStudentByID(@ParamFaken("studentID") int studentID);

    @InsertFaken("insert into teachers (teacherID, name, phoneNumber, encryptedPassword) values (#{teacherID}, #{name}, #{phoneNumber}, #{password})")
    void insertNewTeacher(@ParamFaken("teacherID") Integer teacherID, @ParamFaken("name") String name, @ParamFaken("phoneNumber") String phoneNumber, @ParamFaken("password") String password);

    @InsertFaken("insert into students (studentID, name, phoneNumber, encryptedPassword) values (#{studentID}, #{name},  #{phoneNumber}, #{encryptedPassword})")
    void insertNewStudent(@ParamFaken("studentID") Integer studentID, @ParamFaken("name") String name, @ParamFaken("phoneNumber") String phoneNumber,@ParamFaken("encryptedPassword") String encryptedPassword);
}
