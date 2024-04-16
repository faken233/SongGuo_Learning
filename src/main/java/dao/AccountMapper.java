package dao;

import pojo.SaltMap;
import pojo.Student;
import pojo.Teacher;
import utils.mybatis.anno.InsertFaken;
import utils.mybatis.anno.ParamFaken;
import utils.mybatis.anno.SelectFaken;

public interface AccountMapper {
    @SelectFaken("select * from salts where userID = #{userID}")
    SaltMap getSaltMapByUserID(@ParamFaken("userID") String userID);

    @SelectFaken("select * from teachers where phoneNumber = #{phoneNumber}")
    Teacher getTeacherByPhoneNumber(@ParamFaken("phoneNumber") String phoneNumber);

    @SelectFaken("select * from students where phoneNumber = #{phoneNumber}")
    Student getStudentByPhoneNumber(@ParamFaken("phoneNumber") String phoneNumber);

    @InsertFaken("insert into teachers (teacherID, name, phoneNumber, encryptedPassword) values (#{teacherID}, #{name}, #{phoneNumber}, #{password})")
    int insertNewTeacher(@ParamFaken("teacherID") Integer teacherID, @ParamFaken("name") String name, @ParamFaken("phoneNumber") String phoneNumber, @ParamFaken("password") String password);

    @InsertFaken("insert into students (studentID, name, phoneNumber, encryptedPassword) values (#{studentID}, #{name},  #{phoneNumber}, #{encryptedPassword})")
    int insertNewStudent(@ParamFaken("studentID") Integer studentID, @ParamFaken("name") String name, @ParamFaken("phoneNumber") String phoneNumber,@ParamFaken("encryptedPassword") String encryptedPassword);

    @InsertFaken("insert into salts (userID, salt) values (#{userID}, #{salt})")
    void insertNewSaltMap(@ParamFaken("userID") Integer userID, @ParamFaken("salt") String salt);
}
