package dao;

import utils.mybatis.anno.ParamFaken;
import utils.mybatis.anno.UpdateFaken;

public interface StudentMapper {

    @UpdateFaken("update students set name = #{name}, grade = #{grade}, className = #{className} where studentID = #{studentID}")
    int updateStudent(@ParamFaken("name") String name, @ParamFaken("grade") String grade, @ParamFaken("className") String className, @ParamFaken("studentID") Integer id);
}
