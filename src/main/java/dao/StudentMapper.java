package dao;

import pojo.Course;
import pojo.EnrolledCourseMap;
import utils.mybatis.anno.InsertFaken;
import utils.mybatis.anno.ParamFaken;
import utils.mybatis.anno.SelectFaken;
import utils.mybatis.anno.UpdateFaken;

import java.util.List;

public interface StudentMapper {

    @UpdateFaken("update students set name = #{name}, grade = #{grade}, className = #{className} where studentID = #{studentID}")
    int updateStudent(@ParamFaken("name") String name, @ParamFaken("grade") String grade, @ParamFaken("className") String className, @ParamFaken("studentID") Integer id);

    @SelectFaken("select * from courses where endDateTime > NOW()")
    List<Course> selectParticipateCourses();

    @SelectFaken("select * from enrolledcourses where studentID = #{studentID}")
    List<EnrolledCourseMap> selectAlreadyParticipatedCourses(@ParamFaken("studentID") Integer studentID);

    @InsertFaken("insert into enrolledcourses (studentID, courseID, enrollDateTime) values(#{studentID}, #{courseID}, NOW())")
    int participateCourse(@ParamFaken("studentID") Integer studentID, @ParamFaken("courseID") Integer courseID);
}
