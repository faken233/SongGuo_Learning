package dao;

import pojo.Course;
import utils.mybatis.anno.InsertFaken;
import utils.mybatis.anno.ParamFaken;
import utils.mybatis.anno.SelectFaken;
import utils.mybatis.anno.UpdateFaken;

import java.sql.Timestamp;
import java.util.List;

public interface TeacherMapper {
    @UpdateFaken("update teachers set name = #{name}, email = #{email}, qq = #{qq}, description = #{description} where teacherID = #{teacherID}")
    int updateTeacher(@ParamFaken("name") String name, @ParamFaken("email") String email, @ParamFaken("qq") String qq, @ParamFaken("description") String description, @ParamFaken("teacherID") Integer id);

    @InsertFaken("insert into courses (courseID, teacherID, courseName, description, startDateTime, endDateTime, maxStudents) " +
            "values (#{courseID}, #{teacherID}, #{courseName}, #{description}, #{startDateTime}, #{endDateTime}, #{maxStudents})")
    int addNewCourse(   @ParamFaken("courseID")         Integer courseID,
                        @ParamFaken("teacherID")        Integer teacherID,
                        @ParamFaken("courseName")       String courseName,
                        @ParamFaken("description")      String description,
                        @ParamFaken("startDateTime")    Timestamp startDateTime,
                        @ParamFaken("endDateTime")      Timestamp endDateTime,
                        @ParamFaken("maxStudents")      Integer maxStudents);

    @SelectFaken("select * from courses where teacherID = #{teacherID}")
    List<Course> selectCourses(@ParamFaken("teacherID") Integer teacherID);
}
