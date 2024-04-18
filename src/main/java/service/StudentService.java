package service;

import pojo.Student;

public interface StudentService {
    Student getInfo(int studentID);

    int updateInfo(Student student);
}
