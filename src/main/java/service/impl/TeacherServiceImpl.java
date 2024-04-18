package service.impl;

import dao.AccountMapper;
import dao.TeacherMapper;
import pojo.Teacher;
import service.TeacherService;
import utils.mybatis.utils.MapperProxyFactory;

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
}
