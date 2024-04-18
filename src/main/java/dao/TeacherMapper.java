package dao;

import utils.mybatis.anno.ParamFaken;
import utils.mybatis.anno.UpdateFaken;

public interface TeacherMapper {
    @UpdateFaken("update teachers set name = #{name}, email = #{email}, qq = #{qq}, description = #{description} where teacherID = #{teacherID}")
    int updateTeacher(@ParamFaken("name") String name, @ParamFaken("email") String email, @ParamFaken("qq") String qq, @ParamFaken("description") String description, @ParamFaken("teacherID") Integer id);
}
