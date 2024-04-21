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

    // 查找在开课时间内的可参与的课程
    @SelectFaken("select * from courses where endDateTime > NOW()")
    List<Course> selectParticipatableCourses();

    // 查找指定学生已经参与过的课程ID
    @SelectFaken("select * from enrolledcourses where studentID = #{studentID}")
    List<EnrolledCourseMap> selectAlreadyParticipatedCourses(@ParamFaken("studentID") Integer studentID);

    // 参与新课程
    @InsertFaken("insert into enrolledcourses (studentID, courseID, enrollDateTime) values(#{studentID}, #{courseID}, NOW())")
    int participateCourse(@ParamFaken("studentID") Integer studentID, @ParamFaken("courseID") Integer courseID);
}
