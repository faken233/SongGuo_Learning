package service;

import pojo.Teacher;

public interface TeacherService {
    Teacher getInfo(int id);
    int updateInfo(Teacher teacher);
}
